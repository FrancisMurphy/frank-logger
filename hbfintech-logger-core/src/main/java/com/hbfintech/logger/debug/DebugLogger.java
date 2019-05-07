package com.hbfintech.logger.debug;

import com.hbfintech.logger.AbstractLogger;

public class DebugLogger extends AbstractLogger
{
    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param msg
     * @param arguments
     */
    public void debug(String msg, Object... arguments)
    {
        logger.debug(msg, arguments);
    }
}
