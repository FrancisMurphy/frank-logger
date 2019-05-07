package com.hbfintech.logger.pfme;

import com.hbfintech.logger.AbstractLogger;

public class PerformanceLogger extends AbstractLogger
{
    public void pfme(String msg, Object... arguments)
    {
        logger.info(msg, arguments);
    }
}
