package com.hbfintech.custom.logger.autoconfig;

import com.hbfintech.logger.configuration.CustomConfigBean;
import com.hbfintech.logger.configuration.LoggerConfigBean;
import com.hbfintech.logger.constants.LoggerType;
import org.springframework.core.env.PropertyResolver;

public class SpringPropertiesSystem {
    private PropertyResolver environment;

    private static final String DEBUG_KEY = "custom.logger.debug.turn";

    private static final String BIZ_KEY = "custom.logger.biz.turn";

    private static final String ACCESS_KEY = "custom.logger.access.turn";

    private static final String PFME_KEY = "custom.logger.pfme.turn";

    private static final String EXTRA_KEY = "custom.logger.extra.turn";

    private static final String ERROR_KEY = "custom.logger.error.turn";

    private static final String WARN_KEY = "custom.logger.warn.turn";

    private static final String SYSTEM_KEY = "custom.logger.system.turn";

    public SpringPropertiesSystem(PropertyResolver environment) {
        this.environment = environment;
    }

    public PropertyResolver getEnvironment() {
        return environment;
    }

    public void setEnvironment(PropertyResolver environment) {
        this.environment = environment;
    }

    public CustomConfigBean apply()
    {
        CustomConfigBean  customConfigBean = new CustomConfigBean();

        LoggerConfigBean loggerConfigBean = null;

        System.err.println(environment.getProperty(DEBUG_KEY));


        loggerConfigBean = new LoggerConfigBean();
        loggerConfigBean.setType(LoggerType.Debug);
        if(environment.containsProperty(DEBUG_KEY))
        {
            loggerConfigBean.setTurn(Boolean.valueOf(environment.getProperty(DEBUG_KEY)));
        }
        customConfigBean.getLoggerConfigCache().put(loggerConfigBean.getType(), loggerConfigBean);

        loggerConfigBean = new LoggerConfigBean();
        loggerConfigBean.setType(LoggerType.Biz);
        if(environment.containsProperty(BIZ_KEY))
        {
            loggerConfigBean.setTurn(Boolean.valueOf(environment.getProperty(BIZ_KEY)));
        }
        customConfigBean.getLoggerConfigCache().put(loggerConfigBean.getType(), loggerConfigBean);

        loggerConfigBean = new LoggerConfigBean();
        loggerConfigBean.setType(LoggerType.Access);
        if(environment.containsProperty(ACCESS_KEY))
        {
            loggerConfigBean.setTurn(Boolean.valueOf(environment.getProperty(ACCESS_KEY)));
        }
        customConfigBean.getLoggerConfigCache().put(loggerConfigBean.getType(), loggerConfigBean);


        loggerConfigBean = new LoggerConfigBean();
        loggerConfigBean.setType(LoggerType.Pfme);
        if(environment.containsProperty(PFME_KEY))
        {
            loggerConfigBean.setTurn(Boolean.valueOf(environment.getProperty(PFME_KEY)));
        }
        customConfigBean.getLoggerConfigCache().put(loggerConfigBean.getType(), loggerConfigBean);

        loggerConfigBean = new LoggerConfigBean();
        loggerConfigBean.setType(LoggerType.Extra);
        if(environment.containsProperty(EXTRA_KEY))
        {
            loggerConfigBean.setTurn(Boolean.valueOf(environment.getProperty(EXTRA_KEY)));
        }
        customConfigBean.getLoggerConfigCache().put(loggerConfigBean.getType(), loggerConfigBean);

        loggerConfigBean = new LoggerConfigBean();
        loggerConfigBean.setType(LoggerType.System);
        if(environment.containsProperty(SYSTEM_KEY))
        {
            loggerConfigBean.setTurn(Boolean.valueOf(environment.getProperty(SYSTEM_KEY)));
        }
        customConfigBean.getLoggerConfigCache().put(loggerConfigBean.getType(), loggerConfigBean);

        loggerConfigBean = new LoggerConfigBean();
        loggerConfigBean.setType(LoggerType.Warn);
        if(environment.containsProperty(WARN_KEY))
        {
            loggerConfigBean.setTurn(Boolean.valueOf(environment.getProperty(WARN_KEY)));
        }
        customConfigBean.getLoggerConfigCache().put(loggerConfigBean.getType(), loggerConfigBean);

        loggerConfigBean = new LoggerConfigBean();
        loggerConfigBean.setType(LoggerType.Error);
        if(environment.containsProperty(ERROR_KEY))
        {
            loggerConfigBean.setTurn(Boolean.valueOf(environment.getProperty(ERROR_KEY)));
        }
        customConfigBean.getLoggerConfigCache().put(loggerConfigBean.getType(), loggerConfigBean);

        customConfigBean.prepare();
        return customConfigBean;
    }
}
