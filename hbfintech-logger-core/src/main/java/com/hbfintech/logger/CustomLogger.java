package com.hbfintech.logger;

import com.hbfintech.logger.dto.AccessLogDto;
import com.hbfintech.logger.dto.ExtraLogDto;
import com.hbfintech.logger.dto.PerformanceLogDto;
import com.hbfintech.logger.dto.SystemLogDto;
import com.hbfintech.logger.dto.WarnLogDto;

/**
 * 自定义Logger
 * <p>
 *
 * @author kaylves
 * @since 1.0
 */
public interface CustomLogger
{
    /**
     * debug日志
     * <p>
     * 用法规则与SLF4J一致，例如
     * loogger.debug("debug msg: 1:{},2:{}", "hello","msg2")则输出
     * debug msg: 1:hello,2:msg2
     *
     * @param msg
     * @param arguments
     */
    void debug(String msg, Object... arguments);

    /**
     * 业务日志(INFO)级别
     *
     * @param msg
     * @param arguments
     */
    void biz(String msg, Object... arguments);

    /**
     * warn日志
     *
     * @param debugLogDto
     */
    void warn(WarnLogDto debugLogDto);

    /**
     * 异常日志
     *
     * @param exceptionNo 异常编号
     * @param message     异常描述
     * @param ex          异常类
     */
    void error(String exceptionNo, String message, Exception ex);

    /**
     * 外部接口日志
     *
     * @param extraLog
     */
    void extra(ExtraLogDto extraLog);

    /**
     * 性能日志
     * < performance >1002|某某接口|200</ performance >
     *
     * @param function      功能或接口(XXYY：XX表示一级功能，YY表示二级功能)
     * @param functionName  功能或接口中文名
     * @param timeConsuming
     */
    void pfme(String function, String functionName, Long timeConsuming);

    /**
     * 性能日志
     *
     * @param pfmeLog
     */
    void pfme(PerformanceLogDto pfmeLog);

    /**
     * 系统日志
     *
     * @param systemLog
     */
    void system(SystemLogDto systemLog);

    /**
     * 访问日志
     *
     * @param accessLog
     */
    void access(AccessLogDto accessLog);
}
