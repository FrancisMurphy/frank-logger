package com.hbfintech.logger.dto;

import com.hbfintech.logger.constants.WarnStatus;

/**
 * <告警日志Dto>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class WarnLogDto
{
    public WarnLogDto(String warnNo, String warnName, String warnTime,
            String warnRemark, WarnStatus status)
    {
        super();
        this.warnNo = warnNo;
        this.warnName = warnName;
        this.warnTime = warnTime;
        this.warnRemark = warnRemark;
        this.status = status;
    }

    /**
     * 告警编号
     */
    private String warnNo;

    /**
     * 告警名称
     */
    private String warnName;

    /**
     * 告警时间
     */
    private String warnTime;

    /**
     * 告警描述
     */
    private String warnRemark;

    /**
     * 告警状态
     */
    private WarnStatus status;

    public String getWarnNo()
    {
        return warnNo;
    }

    public void setWarnNo(String warnNo)
    {
        this.warnNo = warnNo;
    }

    public String getWarnName()
    {
        return warnName;
    }

    public void setWarnName(String warnName)
    {
        this.warnName = warnName;
    }

    public String getWarnTime()
    {
        return warnTime;
    }

    public void setWarnTime(String warnTime)
    {
        this.warnTime = warnTime;
    }

    public String getWarnRemark()
    {
        return warnRemark;
    }

    public void setWarnRemark(String warnRemark)
    {
        this.warnRemark = warnRemark;
    }

    public WarnStatus getStatus()
    {
        return status;
    }

    public void setStatus(WarnStatus status)
    {
        this.status = status;
    }
}
