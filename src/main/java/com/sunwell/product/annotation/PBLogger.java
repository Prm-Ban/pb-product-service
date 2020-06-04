package com.sunwell.product.annotation;

import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class PBLogger {
	
    private static final Logger logger = LoggerFactory.getLogger(PBLogger.class);

//	@Pointcut("execution(** com.sunwell.product..*.*(..))")
//	@Pointcut("@annotation(Example)")
	@Pointcut("!within(com.sunwell.product.annotation.*) && execution(* com.sunwell.product..*.*(..))")
    public void standardMethod() {
	}
	
	@Before("standardMethod()")
	public void testlog() {
		try {
			logger.debug("Before is called");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Around("standardMethod()")
//	@Around("@annotation(Example)")
	public Object log(ProceedingJoinPoint jp) throws Throwable {
		Object retval = null;
		try {
			logger.debug("Entering method: " + jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName());
			retval = jp.proceed();
			logger.debug("Returning from method: " + jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName());
			return retval;
		} catch (Throwable e) {
			logger.debug("Exception occured in: " + jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName());
			logger.debug("Message: " + e.getMessage());
			logger.debug(e.getStackTrace().toString());
			throw e;
		}
	}
}
