package com.hbfintech.logger.dto;

/**
 * <ExtraLog Dto>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class ExtraLogDto
{
    /**
     * 请求唯一号，从最初请求发起源头继承
     */
    private String reqSerialNo;

    /**
     * 外部系统编号
     */
    private String channelNo;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 接口URL
     */
    private String url;

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
    private Long timeConsuming;

    public String getReqSerialNo()
    {
        return reqSerialNo;
    }

    public void setReqSerialNo(String reqSerialNo)
    {
        this.reqSerialNo = reqSerialNo;
    }

    public String getChannelNo()
    {
        return channelNo;
    }

    public void setChannelNo(String channelNo)
    {
        this.channelNo = channelNo;
    }

    public String getInterfaceName()
    {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName)
    {
        this.interfaceName = interfaceName;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
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

    public Long getTimeConsuming()
    {
        return timeConsuming;
    }

    public void setTimeConsuming(Long timeConsuming)
    {
        this.timeConsuming = timeConsuming;
    }
}
