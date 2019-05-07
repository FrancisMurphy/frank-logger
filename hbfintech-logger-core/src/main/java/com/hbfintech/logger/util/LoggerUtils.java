package com.hbfintech.logger.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 通用工具类
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public final class LoggerUtils
{
    private static final Logger logger = LoggerFactory
            .getLogger(LoggerUtils.class);

    private LoggerUtils()
    {
        super();
    }

    /**
     * 获取指定异常的堆栈错误信息
     *
     * @param ex 异常实例
     * @return 获取指定异常的堆栈错误信息
     */
    public static String getExceptionMessage(Throwable ex)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }

    /**
     * 判断是否为空
     *
     * @param content
     * @return
     */
    public static boolean isEmpty(String content)
    {
        return content == null || "".equals(content.trim());
    }

    /**
     * 判断是否不为空
     *
     * @param content
     * @return
     */
    public static boolean isNotEmpty(String content)
    {
        return content == null || "".equals(content.trim());
    }

    /**
     * null转空字符串
     *
     * @param content
     * @return
     */
    public static String nullToEmpty(Object content)
    {
        return content == null ? "" : content.toString();
    }

    public static String inputStream2String(InputStream inputStream)
    {
        Scanner scanner = new Scanner(inputStream);
        StringBuffer sb = new StringBuffer();
        try
        {
            if (scanner != null)
            {
                while (scanner.hasNext())
                {
                    sb.append(scanner.nextLine());
                }
            }
        }
        finally
        {
            scanner.close();
        }
        return sb.toString();
    }

    public static Date string2Date(String value, String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try
        {
            date = sdf.parse(value);
        }
        catch (ParseException e)
        {
            logger.error("can not format " + value + " use " + format);
        }
        return date;
    }

    /**
     * <一句话功能简述> <功能详细描述>
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 返回当前天数之前或之后任意天数的日期
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(int date, String format)
    {
        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat(format);
        calendar1.add(Calendar.DATE, date);
        return sdf1.format(calendar1.getTime());
    }

    /**
     * <一句话功能简述> <功能详细描述>
     *
     * @param in
     * @param inApplyPattern
     * @param outApplyPattern
     * @return
     */
    public static String formatDate(String in, String inApplyPattern,
            String outApplyPattern)
    {
        Date date = string2Date(in, inApplyPattern);
        return formatDate(date, outApplyPattern);
    }

    public static String getRemotedIp(HttpServletRequest request)
    {
        if (request == null)
        {
            return "";
        }

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }

        return ip.trim();
    }

    public static String buildRequestUrl(HttpServletRequest r)
    {
        return buildRequestUrl(r.getServletPath(), r.getRequestURI(),
                r.getContextPath(), r.getPathInfo(), r.getQueryString());
    }

    public static String buildRequestUrl(String servletPath, String requestURI,
            String contextPath, String pathInfo, String queryString)
    {

        StringBuilder url = new StringBuilder();

        if (servletPath != null)
        {
            url.append(servletPath);
            if (pathInfo != null)
            {
                url.append(pathInfo);
            }
        }
        else
        {
            url.append(requestURI.substring(contextPath.length()));
        }

        if (queryString != null)
        {
            url.append("?").append(queryString);
        }

        return url.toString();
    }

    public static Map<String, String> getParameterMap(
            HttpServletRequest request)
    {
        Map<String, String[]> properties = request.getParameterMap();

        Map<String, String> returnMap = new HashMap<>();

        Iterator<Map.Entry<String, String[]>> entries = properties.entrySet()
                .iterator();

        Map.Entry<String, String[]> entry = null;

        String value = "";

        while (entries.hasNext())
        {
            entry = entries.next();

            String[] values = entry.getValue();

            for (int i = 0; i < values.length; i++)
            {
                value = values[i] + ",";
            }
            value = value.substring(0, value.length() - 1);
            returnMap.put(entry.getKey(), value);
        }

        return returnMap;
    }
}
