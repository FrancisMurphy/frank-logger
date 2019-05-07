package com.hbfintech.logger.logging.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper
{
    private DelegatingServletInputStream inputStream;

    public CustomHttpServletRequestWrapper(HttpServletRequest request)
    {
        super(request);
    }

    @Override public ServletInputStream getInputStream() throws IOException
    {
        if (inputStream == null)
        {
            return super.getInputStream();
        }

        return inputStream;
    }

    public void overrideInputStream(byte[] buf)
    {
        this.inputStream = new DelegatingServletInputStream(
                new ByteArrayInputStream(buf));
    }

}
