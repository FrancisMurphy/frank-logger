package com.hbfintech.logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbfintech.logger.configuration.CustomConfigBean;
import com.hbfintech.logger.configuration.LoggerConfigBean;
import com.hbfintech.logger.configuration.PatternType;
import com.hbfintech.logger.exception.LoggerInitException;
import com.hbfintech.logger.pattern.PackagePattern;
import com.hbfintech.logger.pattern.Pattern;
import com.hbfintech.logger.pattern.UrlPattern;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class LoggerContext implements ILoggerContext
{
    /**
     * the logger
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * cacheLogger
     */
    private Map<String, ILogger> cacheLogger;

    private CustomConfigBean customConfig;

    private Map<PatternType, Pattern> patterns;

    /**
     * LoggerConfig
     */
    private LoggerConfigBean loggerConfig;

    public LoggerContext()
    {
        super();
        init();
    }

    private void init()
    {
        this.cacheLogger = new ConcurrentHashMap<>();
        this.patterns = new ConcurrentHashMap<>();

        patterns.put(PatternType.Package, new PackagePattern());
        patterns.put(PatternType.Url, new UrlPattern());
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param name
     * @return
     */
    public ILogger getLogger(String name)
    {
        if (cacheLogger.containsKey(name))
        {
            return cacheLogger.get(name);
        }
        return new DefaultLoggerImpl(name, this);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param name
     * @return
     */
    public CustomLogger getCustomLogger(String name)
    {
        return new DefaultCustomLogger(getLogger(name), customConfig);
    }

    public LoggerConfigBean getLoggerConfig()
    {
        return this.loggerConfig;
    }

    public void setLoggerConfig(LoggerConfigBean loggerConfig)
    {
        this.loggerConfig = loggerConfig;
    }

    public CustomConfigBean getCustomConfig()
    {
        return customConfig;
    }

    public void setCustomConfig(CustomConfigBean customConfig)
    {
        this.customConfig = customConfig;
    }

    @Override public void afterInitialize()
    {
        checkConfig();
    }

    private void checkConfig()
    {
        if (customConfig == null)
        {
            throw new LoggerInitException();
        }
    }

    public boolean matches(PatternType patternType, String pattern)
    {
        return patterns.get(patternType).maches(pattern);
    }
}
