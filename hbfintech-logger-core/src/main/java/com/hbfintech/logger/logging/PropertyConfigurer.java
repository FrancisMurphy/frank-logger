package com.hbfintech.logger.logging;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePropertySource;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0.1
 */
public class PropertyConfigurer extends PropertyPlaceholderConfigurer
        implements ApplicationContextAware
{
    private static final int DEFAULT_BUFFER_LENGTH = 50;

    private Properties props;

    private ApplicationContext applicationContext;

    private String resourceName;

    @Override protected Properties mergeProperties() throws IOException
    {
        Properties tempPro = super.mergeProperties();
        if (applicationContext != null)
        {
            addPropertySource(applicationContext, tempPro);
        }
        this.props = tempPro;
        return tempPro;
    }

    @Override public void setLocation(Resource location)
    {
        super.setLocation(location);
        this.resourceName = location.getDescription();
    }

    @Override public void setLocations(Resource... locations)
    {
        super.setLocations(locations);
        int len;
        if (locations != null && (len = locations.length) > 0)
        {
            StringBuilder sb = new StringBuilder(len * DEFAULT_BUFFER_LENGTH);
            sb.append("[ ").append(locations[0].getDescription());
            for (int i = 1; i < len; i++)
            {
                sb.append(", ").append(locations[i].getDescription());
            }
            sb.append(" ]");
            this.resourceName = sb.toString();
        }
    }

    @Override public void setApplicationContext(
            ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
        if (props != null)
        {
            addPropertySource(applicationContext, props);
        }
    }

    /**
     * 规避Spring 的 bug(xml配置方式，无法通过 Environment获得属性值)<br/>
     * 主动添加应用配置对应的 PropertySource 以解决问题
     *
     * @param applicationContext
     * @param props
     */
    private void addPropertySource(ApplicationContext applicationContext,
            Properties props)
    {
        ConfigurableEnvironment env = (ConfigurableEnvironment) applicationContext
                .getEnvironment();
        PropertySource<?> src = null;
        String resName = resourceName != null ? resourceName : "AppProps";
        try
        {
            Constructor<?> con = ResourcePropertySource.class
                    .getDeclaredConstructor(String.class, String.class,
                            Map.class);
            con.setAccessible(true);
            src = (ResourcePropertySource) con
                    .newInstance(resName, null, props);
        }
        catch (Exception e)
        {
            src = new PropertiesPropertySource(resName, props);
        }
        if (src != null)
        {
            // add PropertySource
            env.getPropertySources().addLast(src);
        }
    }

}
