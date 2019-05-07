package com.hbfintech.logger;

import org.junit.Test;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class ILoggerTest
{
    /**
     * the logger
     */
    private ILogger logger = LoggerFactory.getLogger(ILoggerTest.class);

    @Test public void test()
    {
        System.out.println("hello");
        for (int i = 0; i < 10; i++)
        {
            logger.debug("debug>>>>>>>>>>>>>>>>>>>>>>>>>>>>{}", i);
            logger.warn("warn>>>>>>>>>>>>>>>>>>>>>>>>>>>>{}", i);
            logger.error("error>>>>>>>>>>>>>>>>>>>>>>>>>>>>{}", i);
            logger.access("access {} logger>>>>>>>>>>>>>>>>>>>>>>>>>>", i);
            logger.system("access {} logger>>>>>>>>>>>>>>>>>>>>>>>>>>", i);
        }
    }

}
