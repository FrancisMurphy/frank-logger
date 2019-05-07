package com.hbfintech.logger;

/**
 * <LoggerThreadData>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public final class LoggerThreadData
{

    private static ThreadLocal<String> loggerClass = new ThreadLocal<>();

    private static ThreadLocal<LoggerActionContext> loggerData = new ThreadLocal<>();

    private LoggerThreadData()
    {
        super();
    }

    /**
     * <设置当前线程Logger的Class>
     * <功能详细描述>
     *
     * @param className
     */
    public static void setLoggerClass(String className)
    {
        loggerClass.set(className);
    }

    /**
     * <返回当前线程Logger的Class>
     * <功能详细描述>
     *
     * @return
     */
    public static String getLoggerClass()
    {
        return loggerClass.get();
    }

    /**
     * <设置当前LoggerActionContext>
     * <功能详细描述>
     *
     * @param data
     */
    public static void setLoggerActionContext(LoggerActionContext data)
    {
        loggerData.set(data);
    }

    /**
     * <返回当前LoggerActionContext>
     * <功能详细描述>
     *
     * @return
     */
    public static LoggerActionContext getLoggerActionContext()
    {
        return loggerData.get();
    }

    public static void clearLoggerThreadData()
    {
        loggerData.remove();
        loggerClass.remove();
    }
}
