package sample02_Java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloSpring {
	
	public static void main(String[] args) {
		MessageBean messageBean = new MessageBeanImpl(); //부모 = 자식 생성은 부모로 해준다 new 자식
		
		MessageBean proxy = (MessageBean) Proxy.newProxyInstance(
				MessageBeanImpl.class.getClassLoader(), 
				new Class[] {MessageBean.class},  //인터페이스는 클래스의 타입으로 여러개가 와도 상관이없다
				new InvocationHandler() { //구현부 
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("입실 체크"); //공통
						
						Object ob = method.invoke(messageBean, args); // 핵심코드 호출
						
						System.out.println("퇴실 체크");//공통
						return ob;
					}
				}); //로더할 파일 핸들러, 구현부 익명이너클래스
		
		proxy.study(); // 호출
		System.out.println("---------------------");
		System.out.println("결과 = " + proxy.game());
	}
}
