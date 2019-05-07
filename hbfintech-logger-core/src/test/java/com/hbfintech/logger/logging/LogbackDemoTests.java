package com.hbfintech.logger.logging;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class LogbackDemoTests extends BaseServiceTest
{
    private Logger logger = LoggerFactory.getLogger(LogbackDemoTests.class);

    @Autowired private Environment environment;

    @Test public void environmentTest()
    {
        String s = environment.getProperty("logger.root.dir");
        logger.info("get environment :{}", s);
        customLogger.biz("22222222222222222");
    }
}
