/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:LogAspect.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.techFacilities;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by zpy on 2018/8/4.
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logAspectLogger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.zpy.yearn.facade.*.*(..))" +
            "&& execution(public * com.zpy.yearn.utils.*.*(..))" +
            "&& execution(public * com.zpy.yearn.service.*.*(..))" +
            "&& execution(public * com.zpy.yearn.facilities.*.*(..))"+
            "&& execution(public * com.zpy.yearn.structure.factory.*.*(..))"+
            "&& !execution(public * com.zpy.yearn.facade.*.toString(..))"+
            "&& !execution(public * com.zpy.yearn.techFacilities.LogAspect.*(..))")
    public void log() {
    }

    @Before("log()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        logCallInfo(joinPoint);

    }

    private Logger logCallInfo(JoinPoint joinPoint) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getClass());

        logger.debug("class: {}, method: {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
        logger.debug("object: {}", joinPoint.getTarget());
        logger.debug("args: {}", Arrays.toString(joinPoint.getArgs()));

        return logger;
    }

    @AfterThrowing(pointcut = "log()", throwing = "e")
    public void doException(JoinPoint jp, Throwable e) {
        if (e != null) {
            Logger logger = logCallInfo(jp);
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }


}
