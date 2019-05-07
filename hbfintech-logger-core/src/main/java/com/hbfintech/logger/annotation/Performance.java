package com.hbfintech.logger.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 性能日志注解
 * <p>
 * 对于标记了该注解的方法，切面会自动做方法性能监控
 *
 * @author kaylves
 * @since 1.0
 */
@Documented @Retention(RetentionPolicy.RUNTIME) @Target({ ElementType.TYPE,
        ElementType.METHOD }) public @interface Performance
{
    /**
     * 功能名称或接口名称
     *
     * @return
     */
    String function() default "";

    /**
     * 功能或接口中文明
     *
     * @return
     */
    String functionName();
}
