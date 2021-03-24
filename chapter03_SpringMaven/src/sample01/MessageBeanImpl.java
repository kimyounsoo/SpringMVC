package sample01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

//핵심관심사항, Target
//아래 오버라이드 메소드들은 joinPoint
//그 중에서 showPrintBefore,viewPrintBefore은 pointCut
//@Component
@ComponentScan("spring.conf")
public class MessageBeanImpl implements MessageBean {

	private String str;
	
	@Autowired
	public void setStr(@Value("Have a nice day!!")String str) {
		this.str = str;
	}

	@Override
	public void showPrintBefore() {
		System.out.println("showPrintBefore 메시지 = " + str);
	}

	@Override
	public void viewPrintBefore() {
	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("viewPrintBefore 메시지 = " + str);
	}


	@Override
	public void showPrintAfter() {
		System.out.println("showPrintAfter 메시지 = " + str);
		
	}

	@Override
	public void viewPrintAfter() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("viewPrintAfter 메시지 = " + str);
		
	}
	
	@Override
	public String showPrint() {
		System.out.println("showPrintAfter 메시지 = " + str);
		
		return "스프링!!";
		
	}

	@Override
	public void viewPrint() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("viewPrintAfter 메시지 = " + str);
		
	}	
	
	@Override
	public void display() {
		System.out.println("display 메시지 = "+str);
		
	}
}
