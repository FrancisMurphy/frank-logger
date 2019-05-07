package com.hbfintech.logger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class Slf4jLoggerTest
{
    /**
     * the logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test public void test()
    {
        ch.qos.logback.classic.Logger logback = (ch.qos.logback.classic.Logger) logger;
        logback.debug("{}", "hello");
    }

    @Test public void loggerTest()
    {
        logger.trace("trace>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        logger.debug("debug>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        logger.info("info>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        logger.warn("warn>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        logger.error("error>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
