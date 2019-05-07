package com.hbfintech.logger;

import com.hbfintech.logger.configuration.LoggerConfigBean;

/**
 * <ILogger应用上下文>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public interface ILoggerContext extends ILoggerFactory
{
    /**
     * <获取Logger配置信息>
     * <功能详细描述>
     *
     * @return
     */
    LoggerConfigBean getLoggerConfig();

    /**
     * <设置loggerConfig>
     * <功能详细描述>
     *
     * @param loggerConfig
     */
    void setLoggerConfig(LoggerConfigBean loggerConfig);

    /**
     * <初始化完成后执行>
     * <功能详细描述>
     */
    void afterInitialize();
}
