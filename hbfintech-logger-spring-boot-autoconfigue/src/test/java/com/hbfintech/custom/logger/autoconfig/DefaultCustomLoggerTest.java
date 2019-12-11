package com.hbfintech.custom.logger.autoconfig;

import com.hbfintech.logger.CustomLogger;
import com.hbfintech.logger.LoggerFactory;
import org.junit.Test;

public class DefaultCustomLoggerTest {

    protected CustomLogger logger = LoggerFactory.getCustomLogger(SpringBootAutoConfigListenerTest.class);

    @Test
    public void test()
    {
        System.err.println(">>>>>>>>>>>>>>>>>>> start >>>>>>>>>>>>>>>>>>>>");
        logger.biz("test biz log>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.err.println(">>>>>>>>>>>>>>>>>>> end  >>>>>>>>>>>>>>>>>>>>");
    }
}
