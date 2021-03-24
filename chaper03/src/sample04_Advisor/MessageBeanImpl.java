package sample04_Advisor;



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

	@Override
	public void lesson() {
		System.out.println("컴퓨터 레슨을 한다");
		
	}

}
