package com.hbfintech.logger.configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hbfintech.logger.constants.LoggerType;

/**
 * CustomConfigBean
 *
 * @author kaylves
 * @since 1.0
 */
public class CustomConfigBean
{
    private static final Integer DEFAULT_AUTO_SCAN = 300;

    private Integer autoScan = DEFAULT_AUTO_SCAN;

    private Map<LoggerType, LoggerConfigBean> loggerConfigCache = new ConcurrentHashMap<>();

    private boolean enableDebug;

    private boolean enableBiz;

    private boolean enableWarn;

    private boolean enableErr;

    private boolean enableAccess;

    private boolean enableExtra;

    private boolean enableSystem;

    private boolean enablePfme;

    public Integer getAutoScan()
    {
        return autoScan;
    }

    public void setAutoScan(Integer autoScan)
    {
        this.autoScan = autoScan;
    }

    public Map<LoggerType, LoggerConfigBean> getLoggerConfigCache()
    {
        return loggerConfigCache;
    }

    public void setLoggerConfigCache(
            Map<LoggerType, LoggerConfigBean> loggerConfigCache)
    {
        this.loggerConfigCache = loggerConfigCache;
    }

    public boolean isEnableDebug()
    {
        return enableDebug;
    }

    public void setEnableDebug(boolean enableDebug)
    {
        this.enableDebug = enableDebug;
    }

    public boolean isEnableBiz()
    {
        return enableBiz;
    }

    public void setEnableBiz(boolean enableBiz)
    {
        this.enableBiz = enableBiz;
    }

    public boolean isEnableWarn()
    {
        return enableWarn;
    }

    public void setEnableWarn(boolean enableWarn)
    {
        this.enableWarn = enableWarn;
    }

    public boolean isEnableErr()
    {
        return enableErr;
    }

    public void setEnableErr(boolean enableErr)
    {
        this.enableErr = enableErr;
    }

    public boolean isEnableAccess()
    {
        return enableAccess;
    }

    public void setEnableAccess(boolean enableAccess)
    {
        this.enableAccess = enableAccess;
    }

    public boolean isEnableExtra()
    {
        return enableExtra;
    }

    public void setEnableExtra(boolean enableExtra)
    {
        this.enableExtra = enableExtra;
    }

    public boolean isEnableSystem()
    {
        return enableSystem;
    }

    public void setEnableSystem(boolean enableSystem)
    {
        this.enableSystem = enableSystem;
    }

    public boolean isEnablePfme()
    {
        return enablePfme;
    }

    public void setEnablePfme(boolean enablePfme)
    {
        this.enablePfme = enablePfme;
    }

    /**
     * 预处理
     */
    public void prepare()
    {
        this.enableDebug = getLoggerConfigBean(LoggerType.Debug).isTurn();
        this.enableBiz = getLoggerConfigBean(LoggerType.Biz).isTurn();
        this.enableWarn = getLoggerConfigBean(LoggerType.Warn).isTurn();
        this.enableErr = getLoggerConfigBean(LoggerType.Error).isTurn();
        this.enableAccess = getLoggerConfigBean(LoggerType.Access).isTurn();
        this.enableExtra = getLoggerConfigBean(LoggerType.Extra).isTurn();
        this.enableSystem = getLoggerConfigBean(LoggerType.System).isTurn();
        this.enablePfme = getLoggerConfigBean(LoggerType.Pfme).isTurn();
    }

    /**
     * 根据<code>LoggerType</code>获取日志配置项信息
     *
     * @param loggerType
     * @return
     */
    public LoggerConfigBean getLoggerConfigBean(LoggerType loggerType)
    {
        return loggerConfigCache.get(loggerType);
    }

}
