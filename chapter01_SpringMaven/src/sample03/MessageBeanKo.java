package sample03;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("messageBean") // 서블릿에서 처럼 어플리케이션.xml 17번쨰 줄 여기에 함축 여기에쓰면 xml 에서는 지워줘야함
@Scope("prototype") // 마찬가지
public class MessageBeanKo implements MessageBean {
	private int num;
	
	public MessageBeanKo() {
		System.out.println("MessageBeanKo 기본 생성자");
	}
	@Override
	public void sayHello(String name) {
		num++;
		System.out.println("num = " + num);
		System.out.println("안녕하세요 "+name+ "!!");
		
	}
	
}