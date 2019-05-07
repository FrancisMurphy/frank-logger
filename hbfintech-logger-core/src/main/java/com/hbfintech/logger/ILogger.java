package com.hbfintech.logger;

/**
 * ILogger
 *
 * @author kaylves
 * @since 1.0
 */
public interface ILogger
{
    /**
     * 调试日志
     *
     * @param msg
     * @param arguments
     */
    void debug(String msg, Object... arguments);

    /**
     * 业务日志
     *
     * @param msg
     * @param arguments
     */
    void biz(String msg, Object... arguments);

    /**
     * 警告日志
     *
     * @param msg
     * @param arguments
     */
    void warn(String msg, Object... arguments);

    /**
     * 异常日志
     *
     * @param msg
     * @param arguments
     */
    void error(String msg, Object... arguments);

    /**
     * 系统日志
     *
     * @param msg
     * @param arguments
     */
    void system(String msg, Object... arguments);

    /**
     * 性能日志
     *
     * @param msg
     * @param arguments
     */
    void pfme(String msg, Object... arguments);

    /**
     * 外部日志
     *
     * @param msg
     * @param arguments
     */
    void extra(String msg, Object... arguments);

    /**
     * 访问日志
     *
     * @param msg
     * @param arguments
     */
    void access(String msg, Object... arguments);

    /**
     * 是否开启Debug
     *
     * @return
     */
    boolean isEnableDebug();

    /**
     * 是否开启Biz日志
     *
     * @return
     */
    boolean isEnableBiz();

    /**
     * 是否开启Warn
     *
     * @return
     */
    boolean isEnableWarn();

    /**
     * 是否开启Error
     *
     * @return
     */
    boolean isEnableError();

    /**
     * 是否开启Extra
     *
     * @return
     */
    boolean isEnableExtra();

    /**
     * 是否开启Pfme
     *
     * @return
     */
    boolean isEnablePfme();

    /**
     * 是否开启Sytem logger
     *
     * @return
     */
    boolean isEnableSystem();

    /**
     * 是否开启Access
     *
     * @return
     */
    boolean isEnableAccess();
}
