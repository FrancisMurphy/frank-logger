package com.hbfintech.logger.logging.web;

import com.alibaba.fastjson.JSONObject;
import com.hbfintech.logger.CustomLogger;
import com.hbfintech.logger.LoggerFactory;
import com.hbfintech.logger.dto.AccessLogDto;
import com.hbfintech.logger.util.LoggerUtils;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 访问日志过滤器
 * <p>
 *
 * @author kaylves
 * @since 1.0
 */
public class CustomAccessLogFilter implements Filter
{
    private static final String PLATFORM_OS = "platformOS";

    private static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * define a custom logger
     */
    protected final CustomLogger logger = LoggerFactory
            .getCustomLogger(CustomAccessLogFilter.class);

    @Override public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        CustomHttpServletRequestWrapper reqWrapper = new CustomHttpServletRequestWrapper(
                req);

        ResponseContextWrapper respWrapper = new ResponseContextWrapper(resp);

        Date beginDate = new Date();

        AccessLogDto accessLog = new AccessLogDto();

        if (req.getContentType() != null)
        {
            if (req.getContentType().toLowerCase().contains(
                    MediaType.APPLICATION_FORM_URLENCODED_VALUE.toLowerCase())
                    || req.getContentType().toLowerCase().contains(
                    MediaType.MULTIPART_FORM_DATA_VALUE.toLowerCase()))
            {
                accessLog.setReqParams(
                        JSONObject.toJSON(LoggerUtils.getParameterMap(req))
                                .toString());
            }

            if (req.getContentType().toLowerCase()
                    .contains(MediaType.APPLICATION_JSON_VALUE.toLowerCase()))
            {
                String body = LoggerUtils
                        .inputStream2String(req.getInputStream());
                accessLog.setReqParams(body);
                reqWrapper.overrideInputStream(body.getBytes("utf-8"));
            }
        }

        //do filter
        chain.doFilter(reqWrapper, respWrapper);

        try
        {
            //此处对日志进行异常捕获防止做日志的时候出现异常，这样可以让程序继续运行下去，从而不影响主线程
            addAccessLog(reqWrapper, respWrapper, accessLog, beginDate);
        }
        catch (Exception e)
        {
            logger.error(e.getClass().getSimpleName(),
                    LoggerUtils.formatDate(new Date(), YYYYMMDDHHMMSS), e);
        }
    }

    private void addAccessLog(CustomHttpServletRequestWrapper req,
            ResponseContextWrapper resp, AccessLogDto accessLog, Date beginDate)
            throws IOException
    {
        Date endDate = new Date();

        accessLog.setReqSerialNo("");
        accessLog.setSession(req.getHeader("token"));

        //如果sessionId不能为空
        if (!LoggerUtils.isEmpty(accessLog.getSession()))
        {
            accessLog.setUsername("");
        }

        accessLog.setUrl(LoggerUtils.buildRequestUrl(req));
        accessLog.setAccessSource(req.getHeader(PLATFORM_OS));
        accessLog.setIp(LoggerUtils.getRemotedIp(req));
        accessLog.setFunction(LoggerUtils.buildRequestUrl(req));

        accessLog.setReqTime(LoggerUtils.formatDate(beginDate, YYYYMMDDHHMMSS));

        accessLog.setResTime(LoggerUtils.formatDate(endDate, YYYYMMDDHHMMSS));

        accessLog.setResStatus(resp.getStatus() + "");

        //response string
        if (resp.getContentType() != null && resp.getContentType()
                .contains(MediaType.APPLICATION_JSON_VALUE))
        {
            accessLog.setResParams(resp.getResult());
        }

        Long value = (endDate.getTime() - beginDate.getTime());
        accessLog.setTimeConsuming(value.intValue());
        logger.access(accessLog);
    }

    @Override public void init(FilterConfig filterConfig)
            throws ServletException
    {
    }

    @Override public void destroy()
    {
    }

}