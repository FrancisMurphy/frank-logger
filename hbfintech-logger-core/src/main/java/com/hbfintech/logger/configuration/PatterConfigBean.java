package com.hbfintech.logger.configuration;

public class PatterConfigBean
{
    private String pattern;

    private PatternType type;

    public PatternType getType()
    {
        return type;
    }

    public void setType(PatternType type)
    {
        this.type = type;
    }

    public String getPattern()
    {
        return pattern;
    }

    public void setPattern(String pattern)
    {
        this.pattern = pattern;
    }

}
