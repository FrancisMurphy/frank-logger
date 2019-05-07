package com.hbfintech.logger;

import org.junit.Test;

import com.hbfintech.logger.constants.ExecuteStatus;
import com.hbfintech.logger.constants.WarnStatus;
import com.hbfintech.logger.dto.AccessLogDto;
import com.hbfintech.logger.dto.ExtraLogDto;
import com.hbfintech.logger.dto.PerformanceLogDto;
import com.hbfintech.logger.dto.SystemLogDto;
import com.hbfintech.logger.dto.WarnLogDto;

/**
 * CustomLoggerTest
 *
 * @author kaylves
 * @since 1.0
 */
public class CustomLoggerTest extends BaseCustomLoggerTest
{
    public String[] urlArray = { "http://www.baidu.com",
            "http://www.google.com", "http://www.qq.com",
            "http://www.weixin.com" };

    /**
     * eachDebugTest
     */
    @Test public void eachDebugTest()
    {
        for (int i = 0; i < 10; i++)
        {
            logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>just test it !!!");
        }
    }

    @Test public void debugTest()
    {
        logger.debug("debug log>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Test public void bizTest()
    {
        logger.biz(
                "biz log >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> {}",
                "test");
    }

    @Test public void debugAndBizTest()
    {
        debugTest();
        bizTest();
    }

    @Test public void warnTest()
    {
        logger.warn(new WarnLogDto("1", "2", "3", "4", WarnStatus.Remind));
    }

    @Test public void systemTest()
    {
        logger.system(
                new SystemLogDto("1", "2", "3", "4", ExecuteStatus.Start));
    }

    @Test public void pfmeTest()
    {
        for (int i = 0; i < 10; i++)
        {
            logger.pfme(new PerformanceLogDto("1", "2",
                    System.currentTimeMillis()));
        }
    }

    @Test public void extraTest()
    {
        for (int i = 0; i < 100; i++)
        {
            ExtraLogDto extraLog = new ExtraLogDto();
            extraLog.setReqSerialNo("reqSerialNo");
            extraLog.setChannelNo("channelNo");
            extraLog.setInterfaceName("interfaceName");
            extraLog.setUrl(urlArray[randInt(4)]);
            extraLog.setReqTime("req Time");
            extraLog.setReqParams("req params");
            extraLog.setResStatus("0");
            extraLog.setResParams("asdf");
            extraLog.setTimeConsuming(System.currentTimeMillis());
            logger.extra(extraLog);
        }
    }

    private int randInt(int area)
    {
        return (int) (Math.random() * area);
    }

    @Test public void accessLogTest()
    {
        AccessLogDto accessLog = new AccessLogDto();
        logger.access(accessLog);
    }
}
