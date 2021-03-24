package sample02_Java;

public class MessageBeanImpl implements MessageBean{

	@Override
	public void study() {
		System.out.println("수업시간에 공부한다"); // 핵심코드
		
	}

	@Override
	public String game() {
		System.out.println("수업시간에 몰래 게임한다"); // 핵심코드
		return "바부팅이";
	}

}
