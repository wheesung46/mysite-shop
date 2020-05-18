package com.bit.myshop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {
	@Around("execution( * *..controller.*.*(..) ) || execution( * *..service.*.*(..) ) || execution( * *..repository.*.*(..) )")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object result = pjp.proceed();
		
		stopWatch.stop();
		
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		
		String taskName = className + "." + methodName;
		
		System.out.println("[ExecutionTime]["
						+ taskName + "] TaskTime : "
						+ stopWatch.getTotalTimeMillis()
						+ "ms");
		return result;
	}
}
