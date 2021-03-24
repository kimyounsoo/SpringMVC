package sample05;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("helloSpring")
public class HelloSpring {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloSpring helloSpring = (HelloSpring) context.getBean("helloSpring");
		helloSpring.menu(context);//호출, 매개변수로 전달 context는 어플리케이션 타입 자바에서 값을 주고받는 방식
		System.out.println("프로그램을 종료합니다.");

	}

	private void menu(ApplicationContext context) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 이렇게 또쓰는건 안되기때문에 매개변수로 데이터를 받아온것이다
		Scanner scan = new Scanner(System.in);
		SungJuk sungJuk = null;
		int num;
		
		while(true) {
			System.out.println();
			System.out.println("*******************");
			System.out.println("   1. 입력");
			System.out.println("   2. 출력");
			System.out.println("   3. 수정");
			System.out.println("   4. 삭제");
			System.out.println("   5. 종료");
			System.out.println("*******************");
			System.out.println("번호 입력 : ");
			num = scan.nextInt();
			if(num==5) break;
			
			if(num==1) {
				sungJuk = (SungJuk) context.getBean("sungJukInput");//부모 = 자식
				
			}else if(num==2) {
				sungJuk = (SungJuk) context.getBean("sungJukOutput");//부모 = 자식
				
			}else if(num==3) {
				sungJuk = (SungJuk) context.getBean("sungJukModify");//부모 = 자식
			}else if(num==4) {
				sungJuk = (SungJuk) context.getBean("sungJukDelete");//부모 = 자식
			}
			
			sungJuk.execute();//호출
			
		}
		
	}

}
