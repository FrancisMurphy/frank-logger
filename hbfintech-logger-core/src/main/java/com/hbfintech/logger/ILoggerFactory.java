package com.hbfintech.logger;

/**
 * <ILogger工厂>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public interface ILoggerFactory
{
    /**
     * <获取ILogger>
     * <功能详细描述>
     *
     * @param clazz
     * @return
     */
    ILogger getLogger(String name);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param clazz
     * @return
     */
    CustomLogger getCustomLogger(String name);
}
