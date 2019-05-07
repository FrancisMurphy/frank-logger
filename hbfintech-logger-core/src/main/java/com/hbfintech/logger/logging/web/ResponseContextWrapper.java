package com.hbfintech.logger.logging.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * HttpServletResponse的包装类
 * <p>
 * <code>com.zxhy.frame.common.web.ResponseContextWrapper.getOutputStream()</code>
 * <code>com.zxhy.frame.common.web.ResponseContextWrapper.getResult()</code>
 * 对javax.servlet.ServletResponse.getOutputStream()进行了包装，用于返回自定义的
 * <code>com.zxhy.frame.common.web.ResponseContextWrapper.ServletOutputStreamWrapper</code>,通过该类可以获取响应的内容
 *
 * @author kaylves
 * @since 1.0
 */
public class ResponseContextWrapper extends HttpServletResponseWrapper
{
    private ServletOutputStreamWrapper outputStream;

    public ResponseContextWrapper(HttpServletResponse response)
    {
        super(response);
    }

    /**
     * 覆盖父类的获取输出流
     * <p>
     * 注意此处可能被调用多次，因此需要同步方法限制<code>ServletOutputStreamWrapper</code>只实例化一次,否则会出现多个ServletOutputStreamWrapper
     *
     * @return
     * @throws IOException
     */
    @Override public synchronized ServletOutputStream getOutputStream()
            throws IOException
    {
        if (outputStream == null)
        {
            outputStream = new ServletOutputStreamWrapper(
                    super.getOutputStream());
        }
        return outputStream;
    }

    @Override public PrintWriter getWriter() throws IOException
    {
        return super.getWriter();
    }

    /**
     * @return
     */
    public String getResult()
    {
        return outputStream.getResult();
    }

    class ServletOutputStreamWrapper extends ServletOutputStream
    {

        private ServletOutputStream outputStream;

        private ByteArrayOutputStream byteArrayOutStream;

        public ServletOutputStreamWrapper(ServletOutputStream outputStream)
        {
            super();
            this.outputStream = outputStream;
            byteArrayOutStream = new ByteArrayOutputStream();
        }

        @Override public void write(int b) throws IOException
        {
            this.outputStream.write(b);
            byteArrayOutStream.write(b);
        }

        @Override public void write(byte[] b) throws IOException
        {
            this.outputStream.write(b);
            byteArrayOutStream.write(b);
        }

        @Override public void write(byte[] b, int off, int len)
                throws IOException
        {
            this.outputStream.write(b, off, len);
            this.byteArrayOutStream.write(b, off, len);
        }

        public String getResult()
        {
            try
            {
                return new String(byteArrayOutStream.toByteArray(), "utf-8");
            }
            catch (UnsupportedEncodingException e)
            {
                //ignore
            }
            finally
            {
                try
                {
                    byteArrayOutStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return "";
        }

        @Override public boolean isReady()
        {
            return this.outputStream.isReady();
        }

        @Override public void setWriteListener(WriteListener listener)
        {
            this.outputStream.setWriteListener(listener);
        }
    }
}
