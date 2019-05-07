package com.hbfintech.logger.access;

import com.hbfintech.logger.AbstractLogger;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class AccessLogger extends AbstractLogger
{
    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param msg
     * @param arguments
     */
    public void access(String msg, Object... arguments)
    {
        logger.info(msg, arguments);
    }
}
