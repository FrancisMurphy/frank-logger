package com.hbfintech.logger.configuration;

public enum PatternType
{
    Url("访问地址", "url"), Package("访问包", "package");

    private String label;

    private String code;

    PatternType(String label, String code)
    {
        this.label = label;
        this.code = code;
    }

    public String getLabel()
    {
        return label;
    }

    public static PatternType transfer(String code)
    {
        PatternType patternType = null;
        for (PatternType logger : PatternType.values())
        {
            if (logger.getCode().equalsIgnoreCase(code))
            {
                patternType = logger;
                break;
            }
        }
        return patternType;
    }

    public String getCode()
    {
        return code;
    }

}
