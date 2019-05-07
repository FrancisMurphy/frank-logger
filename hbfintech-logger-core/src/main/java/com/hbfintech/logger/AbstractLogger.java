package com.hbfintech.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <抽象Logger类>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public abstract class AbstractLogger
{
    /**
     * use slf4j logger
     */
    protected Logger logger = LoggerFactory
            .getLogger(getClass().getPackage().getName());

}
