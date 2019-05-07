package com.hbfintech.logger.constants;

public enum ExecuteStatus
{
    Start("开始"), End("结束");

    private String label;

    ExecuteStatus(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }
}
