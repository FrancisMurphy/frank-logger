package com.hbfintech.logger.pattern;

import com.hbfintech.logger.LoggerActionContext;
import com.hbfintech.logger.LoggerThreadData;
import com.hbfintech.logger.constants.LoggerType;
import com.hbfintech.logger.dto.AccessLogDto;
import com.hbfintech.logger.dto.ExtraLogDto;

/**
 * <Url规则过滤>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class UrlPattern implements Pattern
{

    @Override public boolean maches(String pattern)
    {
        LoggerActionContext loggerActionContext = LoggerThreadData
                .getLoggerActionContext();

        LoggerType loggerType = loggerActionContext.getLoggerType();

        boolean flag = false;
        switch (loggerType)
        {
            case Access:

                AccessLogDto accessLog = (AccessLogDto) loggerActionContext
                        .getParam();
                flag = accessLog.getUrl().startsWith(pattern);
                break;
            case Extra:

                ExtraLogDto extraLog = (ExtraLogDto) loggerActionContext
                        .getParam();
                flag = extraLog.getUrl().startsWith(pattern);
                break;
            default:

                break;
        }

        return flag;
    }

}
