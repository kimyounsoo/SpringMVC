package sample03_Advice;



public class MessageBeanImpl implements MessageBean {

	@Override
	public void study() {
		System.out.println("수업시간에 공부한다");
		
	}

	@Override
	public String game() {
		System.out.println("수업시간에 몰래 게임한다");
		return "바부팅이";
	}


}
