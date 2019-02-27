package com.ms.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect class added to track/log REST end point usage.
 * 
 * @author Mayank
 *
 */
@Aspect
@Component
public class LoggingAspect {
public static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);
  
  @Before("execution(* com.ms.job.controllers.*.*(..))")
  public void logBefore(JoinPoint joinPoint) {
    LOG.info("Executing: {}.{} / {}", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), joinPoint.getArgs());
  }
}
