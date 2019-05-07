package com.hbfintech.logger.dto;

/**
 * <性能日志Dto>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class PerformanceLogDto
{
    /**
     * XXYY：XX表示一级功能，YY表示二级功能
     */
    private String function;

    /**
     * 功能或接口中文名
     */
    private String functionName;

    /**
     * 耗时
     * 单位毫秒
     */
    private Long timeConsuming;

    public PerformanceLogDto(String function, String functionName,
            Long timeConsuming)
    {
        super();
        this.function = function;
        this.functionName = functionName;
        this.timeConsuming = timeConsuming;
    }

    public String getFunction()
    {
        return function;
    }

    public void setFunction(String function)
    {
        this.function = function;
    }

    public String getFunctionName()
    {
        return functionName;
    }

    public void setFunctionName(String functionName)
    {
        this.functionName = functionName;
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
