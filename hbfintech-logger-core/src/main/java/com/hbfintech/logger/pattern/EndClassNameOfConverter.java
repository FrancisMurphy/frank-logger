package com.hbfintech.logger.pattern;

import com.hbfintech.logger.LoggerThreadData;

import ch.qos.logback.classic.pattern.NamedConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * <获取Logger的类名>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class EndClassNameOfConverter extends NamedConverter
{

    @Override protected String getFullyQualifiedName(ILoggingEvent event)
    {
        return LoggerThreadData.getLoggerClass();
    }
}
