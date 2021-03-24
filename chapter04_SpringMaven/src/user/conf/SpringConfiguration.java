package user.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import user.dao.UserDAO;
import user.dao.UserDAOImpl;

//일반클래스가 아니다 bean을설정해주는 클래스다 라는것을 알려준다
@Configuration
public class SpringConfiguration {
	// BasicDataSource가 xml 에서 데이터소스 만들어주는 역할을한다
	// BasicDataSource 의 property 가 세터 함수이기떄문에 세터로 오라클 정보들을 불러온다

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		basicDataSource.setUsername("c##java");
		basicDataSource.setPassword("bit");
		basicDataSource.setMaxTotal(20);
		basicDataSource.setMaxIdle(3);

		return basicDataSource;
	}

	@Bean
	public UserDAO userDAOImpl() {
		UserDAOImpl userDAOImpl = new UserDAOImpl();
		userDAOImpl.setDataSource(dataSource());
		return userDAOImpl;
	}
}
