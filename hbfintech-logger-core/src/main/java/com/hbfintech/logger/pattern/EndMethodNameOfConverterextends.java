package com.hbfintech.logger.pattern;

import com.hbfintech.logger.LoggerThreadData;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * <获取Logger的方法名称>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class EndMethodNameOfConverterextends extends ClassicConverter
{
    @Override public String convert(ILoggingEvent le)
    {
        StackTraceElement[] cda = le.getCallerData();
        String methodName = CallerData.NA;
        if (cda != null && cda.length > 0)
        {
            String tempName;
            for (int i = cda.length - 1; i >= 0; i--)
            {
                tempName = cda[i].getClassName();
                if (tempName.equals(LoggerThreadData.getLoggerClass()))
                {
                    methodName = cda[i].getMethodName();
                    break;
                }
            }
        }
        return methodName;
    }
}
