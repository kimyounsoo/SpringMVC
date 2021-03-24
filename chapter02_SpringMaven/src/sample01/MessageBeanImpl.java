package sample01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageBeanImpl implements MessageBean{
	private @Value("딸기") String fruit; //롬복이 아니라 스프링벨류로 임포트
	private @Value("5000")int cost; // 자료형앞에 기입해준다
	private @Value("3") int qty; // 생성자가 없어도 자체한테 초기값을 부여해버리는 시스템
	
	// private String fruit = "딸기"; 와 같은개념
	@Override
	public void sayHello() {
		System.out.println("과일명 : " + fruit + "\t 단가 : " + cost + "\t 개수 : " + qty);
	}

	@Override
	public void sayHello(String fruit, int cost) {
		System.out.println("과일명 : " + fruit + "\t 단가 : " + cost + "\t 개수 : " + qty);
	}

	@Override
	public void sayHello(String fruit, int cost, int qty) {
		System.out.println("과일명 : " + fruit + "\t 단가 : " + cost + "\t 개수 : " + qty);
	}

}
