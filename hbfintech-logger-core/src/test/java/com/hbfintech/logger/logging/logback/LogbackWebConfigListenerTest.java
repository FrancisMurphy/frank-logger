package com.hbfintech.logger.logging.logback;

import com.hbfintech.logger.logging.logback.web.LogbackWebConfigListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

@RunWith(MockitoJUnitRunner.class) public class LogbackWebConfigListenerTest
{

    private LogbackWebConfigListener logbackWebConfigListener;

    @Mock private ApplicationContext applicationContext;

    @Mock private ServletContextEvent servletContextEvent;

    @Mock private ServletContext servletContext;

    @Mock private ConfigurableEnvironment configurableEnvironment;

    @Before public void setUp() throws Exception
    {
        logbackWebConfigListener = new LogbackWebConfigListener();
    }

    @Test public void contextDestroyedTest()
    {
        when(applicationContext.getEnvironment())
                .thenReturn(configurableEnvironment);
        when(servletContextEvent.getServletContext())
                .thenReturn(servletContext);
        when(servletContext.getRealPath("/")).thenReturn("");
        logbackWebConfigListener.contextInitialized(servletContextEvent);
    }

    @Test public void contextInitializedTest()
    {
    }
}