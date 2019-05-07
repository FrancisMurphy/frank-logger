package com.hbfintech.logger.constants;

/**
 * <告警状态>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public enum WarnStatus
{
    Remind("提醒"), Warn("告警");

    private String label;

    WarnStatus(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }

}
