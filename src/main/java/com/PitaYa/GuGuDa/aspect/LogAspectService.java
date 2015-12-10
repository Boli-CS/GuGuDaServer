package com.PitaYa.GuGuDa.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogAspectService {
	
	Logger logger = LoggerFactory.getLogger(LogAspectService.class);

	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
		logger.info(proceedingJoinPoint.getClass().getName() + "." + proceedingJoinPoint.getTarget().toString() + " begin");
		Object result = null;
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			logger.error(proceedingJoinPoint.getClass().getName() + "." + proceedingJoinPoint.getTarget().toString() + " get an exception");
			e.printStackTrace();
		} finally {
			logger.info(proceedingJoinPoint.getClass().getName() + "." + proceedingJoinPoint.getTarget().toString() + " finished");
		}
		
		logger.info(proceedingJoinPoint.getClass().getName() + "." + proceedingJoinPoint.getTarget().toString() + " successed");
		return result;
	}
}
