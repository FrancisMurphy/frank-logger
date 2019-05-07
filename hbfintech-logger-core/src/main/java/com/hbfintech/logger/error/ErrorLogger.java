package com.hbfintech.logger.error;

import com.hbfintech.logger.AbstractLogger;

public class ErrorLogger extends AbstractLogger
{
    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param msg
     * @param arguments
     */
    public void error(String msg, Object... arguments)
    {
        logger.error(msg, arguments);
    }
}
