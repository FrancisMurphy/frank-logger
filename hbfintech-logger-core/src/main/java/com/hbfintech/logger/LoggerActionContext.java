package com.hbfintech.logger;

import com.hbfintech.logger.constants.LoggerType;

/**
 * <LoggerActionContext>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class LoggerActionContext
{
    public LoggerActionContext(LoggerType loggerType, Object param)
    {
        super();
        this.loggerType = loggerType;
        this.param = param;
    }

    /**
     * LoggerType
     */
    private LoggerType loggerType;

    /**
     * Logger param
     */
    private Object param;

    public LoggerType getLoggerType()
    {
        return loggerType;
    }

    public void setLoggerType(LoggerType loggerType)
    {
        this.loggerType = loggerType;
    }

    public Object getParam()
    {
        return param;
    }

    public void setParam(Object param)
    {
        this.param = param;
    }
}
