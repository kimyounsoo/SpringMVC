package sample01;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor
public class MessageBeanImpl implements MessageBean{
	@NonNull // 원하는것들 에만 달아줘야한다 머리위에하면 다 생겨서 안된다고 한다
	private String fruit;
	@Setter
	private int cost, qty;	
	
	/*
	public MessageBeanImpl() {
		System.out.println("기본생성자 암것도안해도 무조건 부른다");
	}	
	
	public MessageBeanImpl(String fruit) {
		System.out.println("생성자 = "+fruit);
		this.fruit = fruit;

	}	
	
	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	*/
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
