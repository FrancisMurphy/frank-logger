package com.hbfintech.custom.logger.autoconfig;

import com.hbfintech.logger.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;

public class CustomLoggerAutoconfigListener implements GenericApplicationListener {

    protected Logger logger = org.slf4j.LoggerFactory.getLogger(CustomLoggerAutoconfigListener.class);

    public static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 25;

    private static final Class<?>[] SOURCE_TYPES = { SpringApplication.class,ApplicationContext.class };

    private static final Class<?>[] EVENT_TYPES = { ApplicationStartingEvent.class,
            ApplicationEnvironmentPreparedEvent.class, ApplicationPreparedEvent.class,
            ContextClosedEvent.class, ApplicationFailedEvent.class };

    private SpringPropertiesSystem springPropertiesSystem;
    @Override
    public boolean supportsEventType(ResolvableType eventType) {
        return isAssignableFrom(eventType.getRawClass(), EVENT_TYPES);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return isAssignableFrom(sourceType, SOURCE_TYPES);
    }

    private boolean isAssignableFrom(Class<?> type, Class<?>... supportedTypes) {
        if (type != null) {
            for (Class<?> supportedType : supportedTypes) {
                if (supportedType.isAssignableFrom(type)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartingEvent) {
            onApplicationStartingEvent((ApplicationStartingEvent) event);
        }
        else if (event instanceof ApplicationEnvironmentPreparedEvent) {
            onApplicationEnvironmentPreparedEvent((ApplicationEnvironmentPreparedEvent) event);
        }
        else if (event instanceof ApplicationPreparedEvent) {
            onApplicationPreparedEvent((ApplicationPreparedEvent) event);
        }
        else if (event instanceof ContextClosedEvent && ((ContextClosedEvent) event)
                .getApplicationContext().getParent() == null) {
            onContextClosedEvent();
        }
        else if (event instanceof ApplicationFailedEvent) {
            onApplicationFailedEvent();
        }
    }

    private void onApplicationStartingEvent(ApplicationStartingEvent event) {
        synchronized (LoggerFactory.class) {
            if( logger.isDebugEnabled() )
            {
                logger.debug("spring context onApplicationStartingEvent >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> end ");
                logger.debug("spring context onApplicationStartingEvent  hash:"+this.hashCode()+",source:"+event.getSource().getClass() +" init>>>>>>>>>>>>>>" + event.getClass());
                logger.debug("spring context onApplicationStartingEvent >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> end");
            }

            LoggerFactory.INIT_STATS=LoggerFactory.ON_GOING_INITIALIZATION;
        }
    }

    private void onApplicationEnvironmentPreparedEvent(ApplicationEnvironmentPreparedEvent event){
        if(event.getSource().getClass().equals(SpringApplication.class))
        {
            logger.debug("spring context onApplicationEnvironmentPreparedEvent >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            logger.debug("spring context onApplicationEnvironmentPreparedEvent  hash:"+this.hashCode()+",source:"+event.getSource().getClass() +" init>>>>>>>>>>>>>>" + event.getClass());
            springPropertiesSystem = new SpringPropertiesSystem(event.getEnvironment());
            LoggerFactory.performInitialization(springPropertiesSystem.apply());

        }
    }

    private void onApplicationPreparedEvent(ApplicationPreparedEvent applicationPreparedEvent){
        logger.info("Spring 应用准备成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    private void onContextClosedEvent(){

    }

    private void onApplicationFailedEvent(){

    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }
}
