package com.hbfintech.logger.logging;

import com.hbfintech.logger.CustomLogger;
import com.hbfintech.logger.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) @ContextConfiguration(locations = {
        "classpath:config/application.xml" }) @Rollback(true) public class BaseServiceTest
{
    CustomLogger customLogger = LoggerFactory.getCustomLogger(getClass());
}
