package com.hbfintech.logger.configuration.parse;

import java.io.InputStream;

import com.hbfintech.logger.configuration.CustomConfigBean;

/**
 * 配置解析器
 *
 * @author kaylves
 * @since 1.0
 */
public interface ConfigParse
{
    /**
     * 根据指定的输入流解析获取自定义Logger配置项
     *
     * @param inputStream
     * @return
     */
    CustomConfigBean parse(InputStream inputStream);
}
