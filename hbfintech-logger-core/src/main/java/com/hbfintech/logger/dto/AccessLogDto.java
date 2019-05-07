package com.hbfintech.logger.dto;

/**
 * <访问日志Dto>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class AccessLogDto
{
    public AccessLogDto()
    {
        super();
    }

    /**
     * 请求串号
     */
    private String reqSerialNo;

    /**
     * 用户唯一标识
     * 如果用户登录用户名，手机号等
     */
    private String username;

    /**
     * 会话ID
     * 会话ID或者Token
     */
    private String session;

    private String url;

    /**
     * web，android，IOS或者其它第三方渠道
     */
    private String accessSource;

    /**
     * ip
     */
    private String ip;

    /**
     * function
     * XXYY：YY表示一级功能，YY表示二级功能
     */
    private String function;

    /**
     * yyyyMMddhhmmss（ms）
     */
    private String reqTime;

    /**
     * 隐私参数屏蔽，如密码，身份信息等。可以关闭为空
     */
    private String reqParams;

    /**
     * yyyyMMddhhmmss（ms）
     */
    private String resTime;

    /**
     * 正确码与错误码
     */
    private String resStatus;

    /**
     * 隐私参数屏蔽，如密码，身份信息等。可以关闭
     */
    private String resParams;

    /**
     * 单位毫秒
     */
    private int timeConsuming;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getReqSerialNo()
    {
        return reqSerialNo;
    }

    public void setReqSerialNo(String reqSerialNo)
    {
        this.reqSerialNo = reqSerialNo;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getSession()
    {
        return session;
    }

    public void setSession(String session)
    {
        this.session = session;
    }

    public String getAccessSource()
    {
        return accessSource;
    }

    public void setAccessSource(String accessSource)
    {
        this.accessSource = accessSource;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getFunction()
    {
        return function;
    }

    public void setFunction(String function)
    {
        this.function = function;
    }

    public String getReqTime()
    {
        return reqTime;
    }

    public void setReqTime(String reqTime)
    {
        this.reqTime = reqTime;
    }

    public String getReqParams()
    {
        return reqParams;
    }

    public void setReqParams(String reqParams)
    {
        this.reqParams = reqParams;
    }

    public String getResTime()
    {
        return resTime;
    }

    public void setResTime(String resTime)
    {
        this.resTime = resTime;
    }

    public String getResStatus()
    {
        return resStatus;
    }

    public void setResStatus(String resStatus)
    {
        this.resStatus = resStatus;
    }

    public String getResParams()
    {
        return resParams;
    }

    public void setResParams(String resParams)
    {
        this.resParams = resParams;
    }

    public int getTimeConsuming()
    {
        return timeConsuming;
    }

    public void setTimeConsuming(int timeConsuming)
    {
        this.timeConsuming = timeConsuming;
    }
}
