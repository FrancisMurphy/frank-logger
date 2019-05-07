package com.hbfintech.logger.logging;

import com.alibaba.fastjson.JSONObject;
import com.hbfintech.logger.logging.web.CustomAccessLogFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.DelegatingServletInputStream;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class) public class CustomAccessLogFilterTest
{
    private CustomAccessLogFilter customAccessLogFilter;

    @Mock private HttpServletRequest request;

    @Mock private HttpServletResponse response;

    @Mock private FilterChain filterChain;

    @Before public void setUp()
    {
        customAccessLogFilter = new CustomAccessLogFilter();
    }

    @Test public void testJson() throws Exception
    {
        String someToken = "someToken";
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("a", "1");
        jsonMap.put("b", "2");
        jsonMap.put("c", "3");
        InputStream stream = new ByteArrayInputStream(
                JSONObject.toJSON(jsonMap).toString().getBytes());
        when(request.getInputStream())
                .thenReturn(new DelegatingServletInputStream(stream));
        when(request.getHeader("Authorization")).thenReturn(someToken);
        when(request.getContentType())
                .thenReturn(MediaType.APPLICATION_JSON_VALUE);
        when(request.getRequestURI()).thenReturn("http://localhost://");
        when(request.getContextPath()).thenReturn("/");
        when(request.getHeader("X-Forwarded-For")).thenReturn("192.168.0.1");
        customAccessLogFilter.doFilter(request, response, filterChain);
    }

}