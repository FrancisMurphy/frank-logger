package com.hbfintech.logger.logging.logback.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <功能详细描述>
 *
 * @author kaylves
 * @since 4.0.0
 */
public class LogbackWebConfigListener implements ServletContextListener
{

    @Override public void contextDestroyed(ServletContextEvent event)
    {
        WebLogbackConfigurer.shutdownLogging(event.getServletContext());
    }

    @Override public void contextInitialized(ServletContextEvent event)
    {
        WebLogbackConfigurer.initLogging(event.getServletContext());
    }

}
