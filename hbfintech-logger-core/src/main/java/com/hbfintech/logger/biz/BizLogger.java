package com.hbfintech.logger.biz;

import com.hbfintech.logger.AbstractLogger;

/**
 * 业务日志
 *
 * @author kaylves
 * @since 1.0
 */
public class BizLogger extends AbstractLogger
{
    /**
     * 打印业务日志
     *
     * @param msg       业务日志
     * @param arguments 参数
     */
    public void biz(String msg, Object... arguments)
    {
        logger.info(msg, arguments);
    }
}
