package sample04;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@RequiredArgsConstructor
//@Setter
public class MessageBeanImpl implements MessageBean {
	//@NonNull
	private String name;
	private String phone;
	private Outputter outputter; //파일로 출력
	
	public MessageBeanImpl(String name) {
		System.out.println("1. MessageBeanImpl(String name)");
		this.name = name;
	}
	public void setPhone(String phone) {
		System.out.println("5. setPhone(String phone)");
		this.phone = phone;
	}

	public void setOutputter(Outputter outputter) {
		System.out.println("6. setOutputter(Outputter outputter)");
		this.outputter = outputter;
	}

	
	@Override
	public void helloCall() {
		System.out.println("helloCall()");
		outputter.output("이름="+name+"\t 핸드폰="+phone);
	}

}
