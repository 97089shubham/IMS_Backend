package com.licious.InventoryManagement.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut to match all methods in the application
    @Pointcut("execution(* com.licious.InventoryManagement..*(..))")
    public void applicationPackagePointcut() {
        // Pointcut to match all methods in the application
    }

    // Advice that logs before a method execution
    @Before("applicationPackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    // Advice that logs after a method returns
    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with result: {}", joinPoint.getSignature().toShortString(), result);
    }

    // Advice that logs after a method throws an exception
    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception in method: {} with cause: {}", joinPoint.getSignature().toShortString(), error.getCause() != null ? error.getCause() : "NULL");
    }

    // Advice that logs around a method execution
    @Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            logger.info("Method {} executed in {} ms", joinPoint.getSignature().toShortString(), elapsedTime);
            return result;
        }
        catch (IllegalArgumentException e) {
            logger.error("Illegal argument: {} in method: {}", joinPoint.getArgs(), joinPoint.getSignature().toShortString());
            throw e;
        }
    }
}
