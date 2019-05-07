package com.hbfintech.logger.logging.logback.web;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;

import javax.servlet.ServletContext;

import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.ServletContextPropertyUtils;
import org.springframework.web.util.WebUtils;

import com.hbfintech.logger.exception.LoggerInitException;
import com.hbfintech.logger.logging.logback.LoggingInitializationContext;
import com.hbfintech.logger.logging.logback.SpringXmlConfigurator;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.ext.spring.LogbackConfigurer;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0.1
 */
public final class WebLogbackConfigurer
{

    /**
     * Parameter specifying the location of the logback config file
     */
    public static final String CONFIG_LOCATION_PARAM = "logbackConfigLocation";

    /**
     * Parameter specifying whether to expose the web app root system property
     */
    public static final String EXPOSE_WEB_APP_ROOT_PARAM = "logbackExposeWebAppRoot";

    private WebLogbackConfigurer()
    {
    }

    /**
     * Initialize Logback, including setting the web app root system property.
     *
     * @param servletContext the current ServletContext
     * @see org.springframework.web.util.WebUtils#setWebAppRootSystemProperty
     */
    public static void initLogging(ServletContext servletContext)
    {
        // Expose the web app root system property.
        if (exposeWebAppRoot(servletContext))
        {
            WebUtils.setWebAppRootSystemProperty(servletContext);
        }

        // Only perform custom Logback initialization in case of a config file.
        String locationParam = servletContext
                .getInitParameter(CONFIG_LOCATION_PARAM);
        if (locationParam != null)
        {
            // Perform Logback initialization; else rely on Logback's default initialization.
            for (String location : StringUtils
                    .tokenizeToStringArray(locationParam,
                            ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS))
            {
                try
                {
                    // Resolve context property placeholders before potentially resolving real path.
                    location = ServletContextPropertyUtils
                            .resolvePlaceholders(location, servletContext);
                    // Return a URL (e.g. "classpath:" or "file:") as-is; consider a plain file path as relative to the web application root directory.
                    if (!ResourceUtils.isUrl(location))
                    {
                        location = WebUtils
                                .getRealPath(servletContext, location);
                    }

                    // Write log message to server log.
                    servletContext.log("Initializing Logback from [" + location
                            + "]");

                    // Initialize
                    ApplicationContext ctx = WebApplicationContextUtils
                            .getWebApplicationContext(servletContext);
                    ConfigurableEnvironment environment = (ConfigurableEnvironment) ctx
                            .getEnvironment();
                    LoggingInitializationContext initializationContext = new LoggingInitializationContext(
                            environment);
                    LoggerContext loggerContext = (LoggerContext) StaticLoggerBinder
                            .getSingleton().getLoggerFactory();
                    loggerContext.reset();
                    JoranConfigurator configurator = new SpringXmlConfigurator(
                            initializationContext);
                    configurator.setContext(loggerContext);
                    configurator.doConfigure(
                            ctx.getResource(location).getInputStream());
                    break;
                }
                catch (FileNotFoundException ex)
                {
                    servletContext
                            .log("No logback configuration file found at ["
                                    + location + "]");
                }
                catch (Exception e)
                {
                    throw new LoggerInitException(
                            "Unexpected error while configuring logback", e);
                }
            }
        }

        //If SLF4J's java.util.logging bridge is available in the classpath, install it. This will direct any messages
        //from the Java Logging framework into SLF4J. When logging is terminated, the bridge will need to be uninstalled
        try
        {
            Class<?> julBridge = ClassUtils
                    .forName("org.slf4j.bridge.SLF4JBridgeHandler",
                            ClassUtils.getDefaultClassLoader());

            Method removeHandlers = ReflectionUtils
                    .findMethod(julBridge, "removeHandlersForRootLogger");
            if (removeHandlers != null)
            {
                servletContext
                        .log("Removing all previous handlers for JUL to SLF4J bridge");
                ReflectionUtils.invokeMethod(removeHandlers, null);
            }

            Method install = ReflectionUtils.findMethod(julBridge, "install");
            if (install != null)
            {
                servletContext.log("Installing JUL to SLF4J bridge");
                ReflectionUtils.invokeMethod(install, null);
            }
        }
        catch (ClassNotFoundException ignored)
        {
            //Indicates the java.util.logging bridge is not in the classpath. This is not an indication of a problem.
            servletContext
                    .log("JUL to SLF4J bridge is not available on the classpath");
        }
    }

    /**
     * Shut down Logback, properly releasing all file locks
     * and resetting the web app root system property.
     *
     * @param servletContext the current ServletContext
     * @see WebUtils#removeWebAppRootSystemProperty
     */
    public static void shutdownLogging(ServletContext servletContext)
    {
        //Uninstall the SLF4J java.util.logging bridge *before* shutting down the Logback framework.
        try
        {
            Class<?> julBridge = ClassUtils
                    .forName("org.slf4j.bridge.SLF4JBridgeHandler",
                            ClassUtils.getDefaultClassLoader());
            Method uninstall = ReflectionUtils
                    .findMethod(julBridge, "uninstall");
            if (uninstall != null)
            {
                servletContext.log("Uninstalling JUL to SLF4J bridge");
                ReflectionUtils.invokeMethod(uninstall, null);
            }
        }
        catch (ClassNotFoundException ignored)
        {
            //No need to shutdown the java.util.logging bridge. If it's not on the classpath, it wasn't started either.
        }

        try
        {
            servletContext.log("Shutting down Logback");
            LogbackConfigurer.shutdownLogging();
        }
        finally
        {
            // Remove the web app root system property.
            if (exposeWebAppRoot(servletContext))
            {
                WebUtils.removeWebAppRootSystemProperty(servletContext);
            }
        }
    }

    /**
     * Return whether to expose the web app root system property,
     * checking the corresponding ServletContext init parameter.
     *
     * @param servletContext the servlet context
     * @return {@code true} if the webapp's root should be exposed; otherwise, {@code false}
     * @see #EXPOSE_WEB_APP_ROOT_PARAM
     */
    private static boolean exposeWebAppRoot(ServletContext servletContext)
    {
        String exposeWebAppRootParam = servletContext
                .getInitParameter(EXPOSE_WEB_APP_ROOT_PARAM);
        return (exposeWebAppRootParam == null || Boolean
                .valueOf(exposeWebAppRootParam));
    }
}
