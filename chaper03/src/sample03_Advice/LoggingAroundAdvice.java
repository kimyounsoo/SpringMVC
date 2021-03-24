package sample03_Advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggingAroundAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("Around ---> 입실 체크");
		long start = System.currentTimeMillis(); // 시작할때 시스템 시간
		
		Object ob = invocation.proceed(); // 핵심 호출
		
		long end = System.currentTimeMillis(); // 끝날때 시스템 시간
		System.out.println((end-start)/1000+"초");
		System.out.println("Around ---> 퇴실 체크");
		
		return ob;
	}

}
