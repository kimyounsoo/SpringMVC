package sample01;

public class HelloSpring {

	public static void main(String[] args) {
		//MessageBean 메모리 생성
		MessageBean messageBean = new MessageBean(); //new 클래스 + 생성자
		//sayHello 함수 호출
		messageBean.sayHello("Spring");
		
		/*
		 * 만약 sayHello 함수가 static 일경우 -이미 MessageBean 클래스는 메모리에 생성되어있다
		 */

	}

}
