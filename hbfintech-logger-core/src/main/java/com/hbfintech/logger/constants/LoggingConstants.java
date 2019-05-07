package com.hbfintech.logger.constants;

public interface LoggingConstants
{
    /**
     * 请求成功
     */
    String REQUEST_SUCCESS = "SUCCESS";
    /**
     * 请求失败->合作方系统错误
     */
    String REQUEST_ERROR = "ERROR";

    /**
     * 请求超时->（网络问题、合作方系统宕机）		-TIMEOUT
     */
    String REQUEST_TIMEOUT = "TIMEOUT";

    /**
     * 合作方未知异常->错误码未知					-UNKONW
     */
    String REQUEST_CODE_UNKONW = "UNKONW";

    /**
     * 系统内部异常->系统内部无法处理异常			-SYSTEM_ERROR
     */
    String REQUEST_SYSTEM_INNER_ERROR = "SYSTEM_ERROR";
}
