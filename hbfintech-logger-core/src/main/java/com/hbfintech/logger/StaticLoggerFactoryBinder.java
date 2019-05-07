package com.hbfintech.logger;

import com.hbfintech.logger.util.ContextInitializer;

/**
 * <静态工厂绑定>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public final class StaticLoggerFactoryBinder implements ILoggerFactoryBinder
{
    /**
     * 单例
     */
    public static final StaticLoggerFactoryBinder INSTANCE = new StaticLoggerFactoryBinder();

    /**
     * 默认初始化
     */
    private LoggerContext loggerContext = new LoggerContext();

    private ContextInitializer contextInitializer;

    /**
     * 私有化构造方法
     * 创建  StaticLoggerFactoryBinder 的实例.
     */
    private StaticLoggerFactoryBinder()
    {
    }

    public ILoggerFactory getILoggerFactory()
    {
        return loggerContext;
    }

    public void rest()
    {
        contextInitializer.reloadConfig();
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     */
    public void init()
    {
        contextInitializer = new ContextInitializer(loggerContext);
        contextInitializer.autoConfig();
    }

    public void restConfig()
    {
        contextInitializer.reloadConfig();
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @return
     */
    public static StaticLoggerFactoryBinder getSingleton()
    {
        return INSTANCE;
    }
}
