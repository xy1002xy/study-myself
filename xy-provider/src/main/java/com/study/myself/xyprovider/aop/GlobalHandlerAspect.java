package com.study.myself.xyprovider.aop;

import com.alibaba.fastjson.JSON;
import com.study.myself.xycommon.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 * @program: xy-parent
 * @description: 统一拦截
 * @author: wxy
 * @create: 2020-12-01 17:10
 **/
@Aspect
@Component
@Slf4j
@RefreshScope
public class GlobalHandlerAspect {

    @Value("${xy.ip.list}")
    private List<String> ipList;

    @Pointcut("execution(* com.study.myself.xyprovider.controller..*.*(..))")
    public void checkIp() {
    }

    @Around("checkIp()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            // 校验ip是否是指定的ip
            String IP = IpUtil.getIpAddress(attributes.getRequest());
            if (!ipList.contains(IP)) {
                log.info("访问的ip{},现在ip白名单{}", IP, JSON.toJSONString(ipList));
                throw new IllegalArgumentException("该ip不是允许的ip");
            }
            System.out.println(IP);
        }
        return joinPoint.proceed();
    }
}
