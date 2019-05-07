package com.hbfintech.logger.dto;

import com.hbfintech.logger.constants.ExecuteStatus;

public class SystemLogDto
{
    /**
     * 执行编号
     */
    private String executeNo;

    /**
     * 执行名称
     */
    private String executeName;

    /**
     * 执行时间
     */
    private String executeTime;

    /**
     * 执行情况描述
     */
    private String desc;

    /**
     * 执行状态
     */
    private ExecuteStatus status;

    public SystemLogDto(String executeNo, String executeName,
            String executeTime, String desc, ExecuteStatus status)
    {
        super();
        this.executeNo = executeNo;
        this.executeName = executeName;
        this.executeTime = executeTime;
        this.desc = desc;
        this.status = status;
    }

    public String getExecuteNo()
    {
        return executeNo;
    }

    public void setExecuteNo(String executeNo)
    {
        this.executeNo = executeNo;
    }

    public String getExecuteName()
    {
        return executeName;
    }

    public void setExecuteName(String executeName)
    {
        this.executeName = executeName;
    }

    public String getExecuteTime()
    {
        return executeTime;
    }

    public void setExecuteTime(String executeTime)
    {
        this.executeTime = executeTime;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public ExecuteStatus getStatus()
    {
        return status;
    }

    public void setStatus(ExecuteStatus status)
    {
        this.status = status;
    }
}
