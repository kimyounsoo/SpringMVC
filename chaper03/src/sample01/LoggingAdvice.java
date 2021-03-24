package sample01;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

//공통 관심사항, 공통모듈,부가기능, Aspect
public class LoggingAdvice {
	public void beforeTrace() {
		System.out.println("before trace...");
	}
	public void afterTrace() {
		System.out.println("after trace...");
	}
	
	public void trace(ProceedingJoinPoint joinPoint) throws Throwable{
		//공통
		String methodName = joinPoint.getSignature(). toShortString();
		System.out.println("메소드 = " + methodName);
		
		StopWatch sw = new StopWatch();
		sw.start(methodName);
		
		//핵심 관심사항 호출
		joinPoint.proceed();
		
		//공통
		sw.stop();
		System.out.println("처리시간 = "+sw.getTotalTimeMillis()/1000+"초");
	}
}
