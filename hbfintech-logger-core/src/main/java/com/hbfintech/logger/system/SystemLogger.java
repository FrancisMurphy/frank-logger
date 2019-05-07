package com.hbfintech.logger.system;

import com.hbfintech.logger.AbstractLogger;

public class SystemLogger extends AbstractLogger
{
    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param msg
     * @param arguments
     */
    public void system(String msg, Object... arguments)
    {
        logger.info(msg, arguments);
    }
}
