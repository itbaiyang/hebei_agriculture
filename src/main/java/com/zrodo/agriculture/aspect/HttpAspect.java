package com.zrodo.agriculture.aspect;

import com.zrodo.agriculture.util.json.JsonStatus;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * Created by baiyang on 2017/5/24.
 */
@Aspect
@Component
public class HttpAspect {

    private static final String[] IGNORE_URI = {"login", "upload", "insertSample"};
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${token.expire.time}")
    private Long expireTime;

    @Pointcut("execution(public * com.zrodo.agriculture.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public boolean doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        //url
        logger.info("url={}", request.getRequestURL());

        //method
        logger.info("method={}", request.getMethod());

        //ip
        logger.info("ip={}", request.getRemoteAddr());

        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        //参数
        logger.info("args={}", joinPoint.getArgs());

        String url = request.getRequestURL().toString();
        for (String s : IGNORE_URI) {
            if (url.contains(s)) {
                return true;
            }
        }
        String tokenKey = request.getHeader("token");

        if (StringUtils.isBlank(tokenKey)) {
            writeMessageUtf8(response, JsonStatus.tokenExpire());
            return false;
        }

        String tokenValue = redisTemplate.boundValueOps(tokenKey).get();

        // 缓存中没有tokenValue，拦截
        if (null == tokenValue) {
            writeMessageUtf8(response, JsonStatus.tokenExpire());
            return false;
        }

        System.out.println("拦截器:" + tokenValue);
        request.setAttribute("token", tokenValue);
        redisTemplate.boundValueOps(tokenKey).expire(expireTime, TimeUnit.MINUTES);
        return true;
    }

    private void writeMessageUtf8(HttpServletResponse response, String str) {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println(str);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    @After("log()")
    public void doAfter() {
        logger.info("3-3-3-3-3-3-3-3-3-3-3-3");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object.toString());
    }
}
