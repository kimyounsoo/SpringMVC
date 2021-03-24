package sample01;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

//공통 관심사항, 공통모듈,부가기능, Aspect
//LoggingAdvice 가 Aspect 공통 모듈이에요 하고 알려주는 @Aspect xml에 있는사항이라 여기에 써준다
@Aspect //공통모듈인걸 알려주지 않으면 삽입이 하나도 되지 않게된다
//@Component
@ComponentScan("spring.conf")
public class LoggingAdvice {
	//앞부분에 삽입된다고 알려준다 xml에 있는부분 대체, 뒤에 메소드가 실행될 때 삽입된다고도 알려줘야한다
	@Before("execution(public void sample01.MessageBeanImpl.*Before(..))")
	public void beforeTrace() {
		System.out.println("before trace...");
	}
	@After("execution(public * *.*.*After(..))")
	public void afterTrace() {
		System.out.println("after trace...");
	}
	
	@Around("execution(public * *.*.*Print(..))")
	public void trace(ProceedingJoinPoint joinPoint) throws Throwable{
		String methodName = joinPoint.getSignature(). toShortString();
		System.out.println("메소드 = " + methodName);
		
		StopWatch sw = new StopWatch();
		sw.start(methodName);
		
		//핵심 관심사항 호출
		Object ob = joinPoint.proceed();//핵심관심사항 호출
		System.out.println("return 값 = " + ob);
		
		//공통
		sw.stop();
		System.out.println("처리시간 = "+sw.getTotalTimeMillis()/1000+"초");
	}
}
