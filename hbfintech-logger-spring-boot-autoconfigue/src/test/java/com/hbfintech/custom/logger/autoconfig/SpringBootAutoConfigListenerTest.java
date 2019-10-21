package com.hbfintech.custom.logger.autoconfig;

import com.hbfintech.logger.CustomLogger;
import com.hbfintech.logger.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
public class SpringBootAutoConfigListenerTest {

    protected CustomLogger logger = LoggerFactory.getCustomLogger(SpringBootAutoConfigListenerTest.class);

    @Test
    public void test() {
        System.err.println(">>>>>>>>>>>>>>>>>>> start >>>>>>>>>>>>>>>>>>>>");
        logger.biz("test biz log>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.err.println(">>>>>>>>>>>>>>>>>>> end  >>>>>>>>>>>>>>>>>>>>");
    }
}
