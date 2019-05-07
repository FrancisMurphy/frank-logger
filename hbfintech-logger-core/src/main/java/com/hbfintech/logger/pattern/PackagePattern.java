package com.hbfintech.logger.pattern;

import com.hbfintech.logger.LoggerThreadData;

/**
 * <Url规则过滤>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class PackagePattern implements Pattern
{

    @Override public boolean maches(String pattern)
    {
        return LoggerThreadData.getLoggerClass().startsWith(pattern);
    }

}
