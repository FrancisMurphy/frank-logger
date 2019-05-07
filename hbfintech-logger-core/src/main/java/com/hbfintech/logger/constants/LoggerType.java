package com.hbfintech.logger.constants;

/**
 * 日志类型
 *
 * @author kaylves
 * @since 1.0
 */
public enum LoggerType
{
    Debug("调试", "debug"), Biz("业务日志", "biz"), Warn("警告", "warn"), Error("",
        "error"), Access("访问日志", "access"), Extra("extra", "extra"), System(
        "系统日志", "system"), Pfme("性能 日志", "pfme");

    LoggerType(String label, String code)
    {
        this.label = label;
        this.code = code;
    }

    private String label;

    private String code;

    public String getCode()
    {
        return code;
    }

    public String getLabel()
    {
        return label;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param code
     * @return
     */
    public static LoggerType transfer(String code)
    {
        LoggerType loggerType = null;
        for (LoggerType logger : LoggerType.values())
        {
            if (logger.getCode().equalsIgnoreCase(code))
            {
                loggerType = logger;
                break;
            }
        }
        return loggerType;
    }
}
