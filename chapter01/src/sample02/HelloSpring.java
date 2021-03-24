package sample02;

public class HelloSpring {

	public static void main(String[] args) {
		//MessageBeanKo messageBean = new MessageBeanKo();//1:1관계, 결합도100%
		MessageBean messageBean = new MessageBeanKo(); // 결합도 낮추기, 다형성(부모=자식)
		messageBean.sayHello("Spring");
	}

}
