package com.hbfintech.logger.dto;

public class DebugLogDto
{
    public DebugLogDto(String className, String methodName, String msg)
    {
        super();
        this.className = className;
        this.methodName = methodName;
        this.msg = msg;
    }

    private String className;

    private String methodName;

    private String msg;

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getMethodName()
    {
        return methodName;
    }

    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}
