package com.hbfintech.custom.logger.autoconfig;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hbfintech.logger.CustomLogger;
import com.hbfintech.logger.LoggerFactory;
import com.hbfintech.logger.dto.ExtraLogDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Feign外部访问日志
 */
@Aspect
public class FeignClientExtraLoggerAspect
{

    public static final String FEIGN_CLIENT_CLASS_NAME = "org.springframework.cloud.openfeign.FeignClient";

    protected CustomLogger logger = LoggerFactory.getCustomLogger(getClass());

    private ThreadLocal<Date>  requestDates = new ThreadLocal<>();

    @Pointcut("@within(org.springframework.cloud.openfeign.FeignClient)")
    public void feignLogAspect()
    {
    }


    @Before(value = "feignLogAspect()")
    public void checkBefore(JoinPoint joinPoint)
    {
        //Set now datetime for current request
        requestDates.set(new Date());
    }

    @AfterReturning(value = "feignLogAspect()", argNames = "retVal", returning = "retVal")
    public void signAfter(JoinPoint joinPoint, Object retVal)
    {
        Object targetObject = joinPoint.getTarget();
        Class clazz = null;
        String baseUrl = "";
        try {
            clazz = Class.forName(FEIGN_CLIENT_CLASS_NAME);
            Object feignClient = AnnotationUtils.findAnnotation(targetObject.getClass(),clazz);
            Method method = clazz.getMethod("url");
            Object urlObject  = ReflectionUtils.invokeMethod(method , feignClient );

            if(urlObject != null )
            {
                baseUrl = urlObject.toString();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();

        Method method = joinPointObject.getMethod();

        logger.debug("baseUrl ：{}" ,baseUrl);

        String uri = "";


        //RequestMapping
        RequestMapping mapping = AnnotationUtils.findAnnotation(targetObject.getClass(),RequestMapping.class);
        if( mapping != null )
        {
            uri+=mapping.value()[0];
        }


        PostMapping postMapping = AnnotationUtils.getAnnotation(method, PostMapping.class);
        if(postMapping==null)
        {
            GetMapping getMapping = AnnotationUtils.getAnnotation(method, GetMapping.class);
            uri += getMapping.value()[0];
        }
        else
        {
            uri += postMapping.value()[0];
        }

        String reqParamStr = null;

        Object[] args = joinPoint.getArgs();
        if( args!=null )
        {
            if(args.length==1)
            {
                reqParamStr = JSONObject.toJSON(args[0]).toString();
            }
            else
            {
                reqParamStr = JSONArray.toJSON(args).toString();
            }
        }

        ExtraLogDto logDto = new ExtraLogDto();
        logDto.setInterfaceName(uri);
        logDto.setUrl(baseUrl+uri);
        logDto.setChannelNo(null);
        logDto.setReqSerialNo(null);
        logDto.setReqParams(reqParamStr);
        logDto.setResParams(JSONObject.toJSON(retVal).toString());
        logDto.setResTime(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));
        logDto.setTimeConsuming(new Date().getTime()-requestDates.get().getTime());
        logger.extra(logDto);
    }

}
