package com.hbfintech.logger.extra;

import com.hbfintech.logger.AbstractLogger;

public class ExtraLogger extends AbstractLogger
{
    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param msg
     * @param arguments
     */
    public void extra(String msg, Object... arguments)
    {
        logger.warn(msg, arguments);
    }
}
