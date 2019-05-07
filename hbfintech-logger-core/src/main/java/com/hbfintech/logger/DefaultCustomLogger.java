package com.hbfintech.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hbfintech.logger.configuration.CustomConfigBean;
import com.hbfintech.logger.constants.LoggerType;
import com.hbfintech.logger.dto.AccessLogDto;
import com.hbfintech.logger.dto.ErrorLogDto;
import com.hbfintech.logger.dto.ExtraLogDto;
import com.hbfintech.logger.dto.PerformanceLogDto;
import com.hbfintech.logger.dto.SystemLogDto;
import com.hbfintech.logger.dto.WarnLogDto;
import com.hbfintech.logger.util.LoggerUtils;

/**
 * Default customeLogger implementation
 *
 * @author kaylves
 * @since 1.0
 */
public class DefaultCustomLogger implements CustomLogger
{
    private ILogger logger;

    protected CustomConfigBean customConfig;

    public DefaultCustomLogger(ILogger logger, CustomConfigBean customConfig)
    {
        super();
        this.logger = logger;
        this.customConfig = customConfig;
    }

    public void debug(String msg, Object... arguments)
    {
        LoggerThreadData.setLoggerActionContext(
                new LoggerActionContext(LoggerType.Debug, arguments));
        logger.debug(msg, arguments);
    }

    @Override public void biz(String msg, Object... arguments)
    {
        LoggerThreadData.setLoggerActionContext(
                new LoggerActionContext(LoggerType.Biz, arguments));
        logger.debug(msg, arguments);
    }

    public void warn(WarnLogDto debugLogDto)
    {
        LoggerThreadData.setLoggerActionContext(
                new LoggerActionContext(LoggerType.Warn, debugLogDto));
        logger.warn("{}|{}|{}|{}|{}", debugLogDto.getWarnNo(),
                debugLogDto.getWarnName(), debugLogDto.getWarnRemark(),
                debugLogDto.getWarnTime(), debugLogDto.getStatus().getLabel());
    }

    /**
     * @see com.hbfintech.logger.CustomLogger#error(com.hbfintech.logger.dto.ErrorLogDto)
     */
    public void error(ErrorLogDto debugLogDto)
    {
        LoggerThreadData.setLoggerActionContext(
                new LoggerActionContext(LoggerType.Error, debugLogDto));
        logger.error("{}|{}|{}|{}", debugLogDto.getExceptionNo(),
                debugLogDto.getMessage(), debugLogDto.getExceptionTime(),
                debugLogDto.getStack());
    }

    @Override public void extra(ExtraLogDto extraLog)
    {
        LoggerThreadData.setLoggerActionContext(
                new LoggerActionContext(LoggerType.Extra, extraLog));
        logger.extra("{}|{}|{}|{}|{}|{}|{}|{}|{}|{}", extraLog.getReqSerialNo(),
                extraLog.getChannelNo(), extraLog.getInterfaceName(),
                extraLog.getUrl(), extraLog.getReqTime(),
                extraLog.getReqParams(), extraLog.getResTime(),
                extraLog.getResStatus(), extraLog.getResParams(),
                extraLog.getTimeConsuming());
    }

    @Override public void pfme(PerformanceLogDto pfmeLog)
    {
        LoggerThreadData.setLoggerActionContext(
                new LoggerActionContext(LoggerType.Pfme, pfmeLog));
        pfme(pfmeLog.getFunction(), pfmeLog.getFunctionName(),
                pfmeLog.getTimeConsuming());
    }

    @Override public void pfme(String function, String functionName,
            Long timeConsuming)
    {
        LoggerThreadData.setLoggerActionContext(
                new LoggerActionContext(LoggerType.Pfme,
                        new PerformanceLogDto(function, functionName,
                                timeConsuming)));
        logger.pfme("{}|{}|{}", function, functionName, timeConsuming);
    }

    public void system(SystemLogDto systemLog)
    {
        LoggerThreadData.setLoggerActionContext(
                new LoggerActionContext(LoggerType.System, systemLog));

        logger.system("{}|{}|{}|{}|{}", systemLog.getExecuteNo(),
                systemLog.getExecuteName(), systemLog.getExecuteTime(),
                systemLog.getDesc(), systemLog.getStatus().getLabel());
    }

    public void access(AccessLogDto accessLog)
    {
        LoggerThreadData.setLoggerActionContext(
                new LoggerActionContext(LoggerType.Access, accessLog));
        logger.access("{}|{}|{}|{}|{}|{}|{}|{}|{}|{}|{}|{}|{}",
                accessLog.getReqSerialNo(), accessLog.getUsername(),
                accessLog.getSession(), accessLog.getUrl(),
                accessLog.getAccessSource(), accessLog.getIp(),
                accessLog.getFunction(), accessLog.getReqTime(),
                LoggerUtils.nullToEmpty(accessLog.getReqParams()),
                accessLog.getResTime(), accessLog.getResStatus(),
                accessLog.getResParams(), accessLog.getTimeConsuming());
    }

    @Override public void error(String exceptionNo, String message,
            Exception ex)
    {
        error(new ErrorLogDto(LoggerUtils.nullToEmpty(exceptionNo),
                LoggerUtils.nullToEmpty(message),
                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()),
                LoggerUtils.getExceptionMessage(ex)));
    }

}
