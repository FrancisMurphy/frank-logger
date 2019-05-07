package com.hbfintech.logger.warn;

import com.hbfintech.logger.AbstractLogger;

public class WarnLogger extends AbstractLogger
{
    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param msg
     * @param arguments
     */
    public void warn(String msg, Object... arguments)
    {
        logger.warn(msg, arguments);
    }
}
