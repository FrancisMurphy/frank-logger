package com.hbfintech.logger.logging.logback;

import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.ElementSelector;
import ch.qos.logback.core.joran.spi.RuleStore;
import org.springframework.core.env.Environment;

/**
 * @author kaylves
 * @since 1.0.1
 */
public class SpringXmlConfigurator extends JoranConfigurator
{

    private LoggingInitializationContext initializationContext;

    public SpringXmlConfigurator(
            LoggingInitializationContext initializationContext)
    {
        this.initializationContext = initializationContext;
    }

    @Override public void addInstanceRules(RuleStore rs)
    {
        super.addInstanceRules(rs);
        Environment environment = this.initializationContext.getEnvironment();
        rs.addRule(new ElementSelector("configuration/springProperty"),
                new SpringPropertyAction(environment));
    }

}
