package com.PitaYa.GuGuDa.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogAspectService {
	
	Logger logger = LoggerFactory.getLogger(LogAspectService.class);

	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
		logger.info(proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName() + " begin");
		Object result = null;
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			logger.error(proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName() + " get an exception");
			e.printStackTrace();
		} finally {
			logger.info(proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName() + " finished");
		}
		
		logger.info(proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName() + " successed");
		return result;
	}
}
