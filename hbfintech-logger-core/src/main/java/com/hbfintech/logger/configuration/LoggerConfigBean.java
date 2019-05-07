package com.hbfintech.logger.configuration;

import java.util.List;

import com.hbfintech.logger.constants.LoggerType;

/**
 * Logger配置项
 *
 * @author kaylves
 * @since 1.0
 */
public class LoggerConfigBean
{
    /**
     * logger类型
     */
    private LoggerType type;

    /**
     * 开关状态，默认开启
     */
    private boolean turn = true;

    /**
     * 白名单规则
     */
    private List<WhiteListConfigBean> whitelists;

    public LoggerType getType()
    {
        return type;
    }

    public void setType(LoggerType type)
    {
        this.type = type;
    }

    public boolean isTurn()
    {
        return turn;
    }

    public void setTurn(boolean turn)
    {
        this.turn = turn;
    }

    public List<WhiteListConfigBean> getWhitelists()
    {
        return whitelists;
    }

    public void setWhitelists(List<WhiteListConfigBean> whitelists)
    {
        this.whitelists = whitelists;
    }
}
