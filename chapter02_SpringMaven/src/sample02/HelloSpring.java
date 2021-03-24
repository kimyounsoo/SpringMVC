package sample02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
		 ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		 Calc calc; 
		 
		 calc = (Calc) context.getBean("calcAdd"); // applicationContext.xml 에서 받아옴
		 calc.calculate();// 그후 계산
		 
			
		 calc = (Calc) context.getBean("calcMul"); 
		 calc.calculate();// 그후 계산
			 	 
		 ((AbstractApplicationContext) context).close();
	}
}
