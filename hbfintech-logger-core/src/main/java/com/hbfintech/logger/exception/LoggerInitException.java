package com.hbfintech.logger.exception;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class LoggerInitException extends RuntimeException
{

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 1L;

    public LoggerInitException()
    {
        super();
    }

    public LoggerInitException(String message)
    {
        super(message);
    }

    public LoggerInitException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
