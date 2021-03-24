package spring.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sample03.SungJukDTO;
import sample03.SungJukImpl;
import sample05.SungJukDTO2;
import sample05.SungJukInput;

@Configuration
//xml에 때려박으면 복잡하고 길어지기때문에 환경설정만 따로 관리하는 클래스이다
//스프링에게 해당 클래스는 빈을 구성해주는 클래스 이다 알려주는 것
public class SpringConfiguration {
	
	//메소드명을 객체명으로 인식한다
	//return 되는애가 bean이에요 하고 알려주는것
//	@Bean 
//	public SungJukDTO sungJukDTO(){
//		return new SungJukDTO();
//	}
	@Bean(name="sungJukDTO")
	public SungJukDTO getSungJukDTO(){
		return new SungJukDTO();
	}
	@Bean
	public SungJukImpl sungJukImpl() {
		return new SungJukImpl();
	}
//	@Bean(name="sungJukImpl")
//	public SungJukImpl getSungJukImpl() {
//		return new SungJukImpl();
//		
//	}
	
//	@Bean
//	public SungJukInput sungJukInput() {
//		return new SungJukInput();
//	}
	
	//ArrayList Bean생성을해서 아이디(list)의 값만 맞춰주면 된다
	//원래는 이론상으론 안해줘도 되는데 이상한현상?? 으로 해줘야한다고한다
	@Bean
	public List<SungJukDTO2> list(){
		return new ArrayList<SungJukDTO2>();
	}
	
}
//@Bean 타입의 객체를 생성하겠따는 의미가 아니라
//return되는 값을 스프링의 bean(빈)으로 인식해라