package com.hbfintech.logger;

import com.hbfintech.logger.access.AccessLogger;
import com.hbfintech.logger.biz.BizLogger;
import com.hbfintech.logger.configuration.LoggerConfigBean;
import com.hbfintech.logger.configuration.WhiteListConfigBean;
import com.hbfintech.logger.constants.LoggerType;
import com.hbfintech.logger.debug.DebugLogger;
import com.hbfintech.logger.error.ErrorLogger;
import com.hbfintech.logger.extra.ExtraLogger;
import com.hbfintech.logger.pfme.PerformanceLogger;
import com.hbfintech.logger.system.SystemLogger;
import com.hbfintech.logger.warn.WarnLogger;

/**
 * 默认日志实现
 *
 * @author kaylves
 * @since 1.0
 */
public class DefaultLoggerImpl implements ILogger
{
    protected String className;

    private DebugLogger debugLogger = new DebugLogger();

    private BizLogger bizLogger = new BizLogger();

    private WarnLogger warnLogger = new WarnLogger();

    private ErrorLogger errorLogger = new ErrorLogger();

    private AccessLogger accessLogger = new AccessLogger();

    private PerformanceLogger pfmeLogger = new PerformanceLogger();

    private SystemLogger systemLogger = new SystemLogger();

    private ExtraLogger extraLogger = new ExtraLogger();

    protected LoggerContext loggerContext;

    public DefaultLoggerImpl(String name, LoggerContext loggerContext)
    {
        this.className = name;
        this.loggerContext = loggerContext;
    }

    public void filterAndDoLogger(LoggerType loggerType, String msg,
            Object... arguments)
    {
        try
        {
            LoggerThreadData.setLoggerClass(className);
            if (matches(loggerType))
            {
                printLoggerByType(loggerType, msg, arguments);
            }
        }
        finally
        {
            LoggerThreadData.clearLoggerThreadData();
        }
    }

    private void printLoggerByType(LoggerType loggerType, String msg,
            Object... arguments)
    {
        switch (loggerType)
        {
            case Debug:
                if (isEnableDebug())
                {
                    debugLogger.debug(msg, arguments);
                }
                break;
            case Biz:
                if (isEnableBiz())
                {
                    bizLogger.biz(msg, arguments);
                }
                break;
            case Warn:
                if (isEnableWarn())
                {
                    warnLogger.warn(msg, arguments);
                }

                break;
            case Error:
                if (isEnableError())
                {
                    errorLogger.error(msg, arguments);
                }
                break;
            case Extra:
                if (isEnableExtra())
                {
                    extraLogger.extra(msg, arguments);
                }
                break;
            case Pfme:
                if (isEnablePfme())
                {
                    pfmeLogger.pfme(msg, arguments);
                }
                break;
            case Access:
                if (isEnableAccess())
                {
                    accessLogger.access(msg, arguments);
                }
                break;
            case System:
                if (isEnableSystem())
                {
                    systemLogger.system(msg, arguments);
                }
                break;

            default:
                break;
        }
    }

    private boolean matches(LoggerType loggerType)
    {
        LoggerConfigBean loggerConfig = loggerContext.getCustomConfig()
                .getLoggerConfigCache().get(loggerType);

        if (loggerConfig.getWhitelists() != null && !loggerConfig
                .getWhitelists().isEmpty())
        {
            for (WhiteListConfigBean whitelist : loggerConfig.getWhitelists())
            {
                if (whitelist.getPattern() != null)
                {
                    whitelist.getPattern().getType();
                    if (!loggerContext.matches(whitelist.getPattern().getType(),
                            whitelist.getPattern().getPattern()))
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * 调试日志
     *
     * @param msg
     * @param arguments
     */
    public void debug(String msg, Object... arguments)
    {
        filterAndDoLogger(LoggerType.Debug, msg, arguments);
    }

    /**
     * biz日志
     *
     * @see com.hbfintech.logger.ILogger#biz(java.lang.String, java.lang.Object[])
     */
    @Override public void biz(String msg, Object... arguments)
    {
        filterAndDoLogger(LoggerType.Biz, msg, arguments);
    }

    /**
     * 警告日志
     *
     * @param msg
     * @param arguments
     */
    public void warn(String msg, Object... arguments)
    {
        filterAndDoLogger(LoggerType.Warn, msg, arguments);
    }

    /**
     * @param msg
     * @param arguments
     */
    public void error(String msg, Object... arguments)
    {
        filterAndDoLogger(LoggerType.Error, msg, arguments);
    }

    public void system(String msg, Object... arguments)
    {
        filterAndDoLogger(LoggerType.System, msg, arguments);
    }

    public void pfme(String msg, Object... arguments)
    {
        filterAndDoLogger(LoggerType.Pfme, msg, arguments);
    }

    public void extra(String msg, Object... arguments)
    {
        filterAndDoLogger(LoggerType.Extra, msg, arguments);
    }

    public void access(String msg, Object... arguments)
    {
        filterAndDoLogger(LoggerType.Access, msg, arguments);
    }

    @Override public boolean isEnableDebug()
    {
        return loggerContext.getCustomConfig().isEnableDebug();
    }

    @Override public boolean isEnableWarn()
    {
        return loggerContext.getCustomConfig().isEnableWarn();
    }

    @Override public boolean isEnableError()
    {
        return loggerContext.getCustomConfig().isEnableErr();
    }

    @Override public boolean isEnableExtra()
    {
        return loggerContext.getCustomConfig().isEnableExtra();
    }

    @Override public boolean isEnablePfme()
    {
        return loggerContext.getCustomConfig().isEnablePfme();
    }

    @Override public boolean isEnableSystem()
    {
        return loggerContext.getCustomConfig().isEnableSystem();
    }

    @Override public boolean isEnableAccess()
    {
        return loggerContext.getCustomConfig().isEnableAccess();
    }

    @Override public boolean isEnableBiz()
    {
        return loggerContext.getCustomConfig().isEnableBiz();
    }

}
