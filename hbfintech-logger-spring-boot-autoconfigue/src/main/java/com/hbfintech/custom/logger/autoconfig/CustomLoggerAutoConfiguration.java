package com.hbfintech.custom.logger.autoconfig;

import com.hbfintech.logger.logging.web.CustomAccessLogFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class CustomLoggerAutoConfiguration
{

    @Bean
    @ConditionalOnClass(Filter.class)
    @ConditionalOnMissingBean
    public FilterRegistrationBean customAccessLogFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CustomAccessLogFilter());
        registration.addUrlPatterns("/*");
        registration.setName("customAccessLogFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    @ConditionalOnClass(name= FeignClientExtraLoggerAspect.FEIGN_CLIENT_CLASS_NAME)
    @ConditionalOnMissingBean
    public FeignClientExtraLoggerAspect feignClientExtraLoggerAspect()
    {
        return new FeignClientExtraLoggerAspect();
    }

}
