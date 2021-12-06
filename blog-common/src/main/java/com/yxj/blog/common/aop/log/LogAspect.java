package com.yxj.blog.common.aop.log;

import com.alibaba.fastjson.JSON;
import com.yxj.blog.common.utils.HttpContextUtils;
import com.yxj.blog.common.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author 从前慢
 * @Aspect 面向切面编程
 *
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.yxj.blog.common.aop.log.LogAnnotation)")
    public void pt(){

    }

    /**
     * 环绕通知
     */
    @Around("pt()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        /**
         * 开始执行方法
         */
        long beginTime = System.currentTimeMillis();
        /**
         * 执行方法
         */
        Object result = joinPoint.proceed();
        /**
         * 结束执行方法
         */
        long overTime = System.currentTimeMillis();
        /**
         * 执行时间（毫秒）
         */
        long time = overTime - beginTime;
        /**
         * 保存日志
         */
        recordLog(joinPoint,time);
        return  result;


    }

    private void recordLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        log.info("=====================log start=====================");
        log.info("module:{}",annotation.module());
        log.info("operation:{}"+annotation.operator());

        /**
         * 请求的方法名
         */
        String className = joinPoint.getTarget().getClass().getName();
        String MethodName = signature.getName();
        log.info("request method:{}",className + "." + MethodName + "{}");


        /**
         * 请求的参数
         */

        Object[] args = joinPoint.getArgs();
        if (0 == args.length) {
            String params = JSON.toJSONString(args);
            log.info("params:{}",params);
        } else if (args[0] instanceof MultipartFile) {
            log.info("params: MultipartFile");
        }else {
            String params = JSON.toJSONString(args);
            log.info("params:{}",params);
        }


        /**
         * 获取request 设置ip地址
         */
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.info("ip:{}", IpUtils.getIpAddr(request));

        log.info("excute time:{} ms",time);
        log.info("=============log end=================");


    }
}
