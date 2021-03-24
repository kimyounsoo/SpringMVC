package spring.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import sample01.LoggingAdvice;
import sample01.MessageBeanImpl;

@Configuration
@EnableAspectJAutoProxy //xml에서 <aop:aspectj-autoproxy/>이거 안쓰려면 이걸써야한다 선택사항
public class SpringConfiguration {
	//메소드명 = bean의  id가 된다
	@Bean
	public MessageBeanImpl messageBeanImpl() {
		return new MessageBeanImpl();// 스프링의 bean으로 생성시켜라
	}
	
	@Bean
	public LoggingAdvice loggingAdvice() {
		return new LoggingAdvice();// 스프링의 bean으로 생성시켜라
	}
}
