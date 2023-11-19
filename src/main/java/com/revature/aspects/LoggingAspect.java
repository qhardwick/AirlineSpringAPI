package com.revature.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Around("everything()")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public Object log(ProceedingJoinPoint pjp) throws Throwable {

        Object result = null;
        Logger log = LogManager.getLogger(pjp.getTarget().getClass());

        log.trace("Method " + pjp.getSignature() + " called with args " + pjp.getArgs());

        try {
            result = pjp.proceed();
        } catch (Throwable t) {
            // If an exception is thrown, log it but then rethrow it so that it can be handled by the controller advice:
            logError(log, t);
            throw t;
        }

        return result;
    }

    private void logError(Logger log, Throwable t) {

        log.error(t.getClass() + " thrown: " + t.getMessage());
        for (StackTraceElement ste : t.getStackTrace()) {
            log.warn(ste);
        }

        // If the exception has a cause, log that too (and so on, recursively):
        if (t.getCause() != null) {
            logError(log, t.getCause());
        }
    }


    @Pointcut("execution(* com.revature..*(..))")
    public void everything() { /* Empty hook */ }
}
