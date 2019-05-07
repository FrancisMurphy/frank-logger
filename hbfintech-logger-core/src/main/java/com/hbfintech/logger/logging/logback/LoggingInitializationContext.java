package com.hbfintech.logger.logging.logback;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0.1
 */
public class LoggingInitializationContext
{

    private final ConfigurableEnvironment environment;

    /**
     * Create a new {@link LoggingInitializationContext} instance.
     *
     * @param environment the Spring environment.
     */
    public LoggingInitializationContext(ConfigurableEnvironment environment)
    {
        this.environment = environment;
    }

    /**
     * Return the Spring environment if available.
     *
     * @return the {@link Environment} or {@code null}
     */
    public Environment getEnvironment()
    {
        return this.environment;
    }

}
