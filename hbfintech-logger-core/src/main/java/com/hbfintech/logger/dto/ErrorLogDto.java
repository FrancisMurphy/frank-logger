package com.hbfintech.logger.dto;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class ErrorLogDto
{
    public ErrorLogDto(String exceptionNo, String message, String exceptionTime,
            String stack)
    {
        super();
        this.exceptionNo = exceptionNo;
        this.message = message;
        this.exceptionTime = exceptionTime;
        this.stack = stack;
    }

    /**
     * 异常编号，系统内自定义
     */
    private String exceptionNo;

    /**
     * 补充描述
     */
    private String message;

    /**
     * 异常时间
     * yyyyMMddhhmmss（ms）
     */
    private String exceptionTime;

    /**
     * 异常堆栈
     */
    private String stack;

    public String getExceptionNo()
    {
        return exceptionNo;
    }

    public void setExceptionNo(String exceptionNo)
    {
        this.exceptionNo = exceptionNo;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getExceptionTime()
    {
        return exceptionTime;
    }

    public void setExceptionTime(String exceptionTime)
    {
        this.exceptionTime = exceptionTime;
    }

    public String getStack()
    {
        return stack;
    }

    public void setStack(String stack)
    {
        this.stack = stack;
    }
}
