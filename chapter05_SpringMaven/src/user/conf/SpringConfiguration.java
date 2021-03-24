package user.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import user.bean.UserDTO;
import user.dao.UserDAOMybatis;
import user.service.UserDeleteService;
import user.service.UserInsertService;
import user.service.UserSelectService;
import user.service.UserService;
import user.service.UserUpdateService;

@Configuration
public class SpringConfiguration {
	// BasicDataSource가 xml 에서 데이터소스 만들어주는 역할을한다
	// BasicDataSource 의 property 가 세터 함수이기떄문에 세터로 오라클 정보들을 불러온다
	// xml의 아이디 명이 메소드 명이 된다
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

	// SqlSessionFactory 생성 xml 처럼
	// SqlSessionFactoryBean 이 아니라 부모인 SqlSessionFactory 을 보내줘야하기때문에 리턴타입에
	// .getObject 해줘야한다
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();// SqlSessionFartoryBean 을생성해줘야
																					// 쓸수있기때문에 생성해준다
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("spring/mybatis-config.xml"));// 리소스 파일경로를 알려주는
																									// 메소드 이다
//		sqlSessionFactoryBean.setConfigLocation(
//				new DefaultResourceLoader().getResource("spring/mybatis-config.xml")); // 김군버전
		sqlSessionFactoryBean.setDataSource(dataSource()); // xml의 레퍼런스 ref를 가져오길 원한다
		return sqlSessionFactoryBean.getObject();
	}

	// sqlSession 생성 xml 처럼 SqlSessionTemplate
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());// xml의 레퍼런스 ref를 가져오길 원한다
	}
	
	// transactionManager 생성 xml 처럼 DataSourceTransactionManager
	@Bean
	public DataSourceTransactionManager transactionManager() throws Exception {
		return new DataSourceTransactionManager(dataSource());// xml의 레퍼런스 ref를 가져오길 원한다
	}

	@Bean
	public UserDTO userDTO() {
		return new UserDTO();
	}

	@Bean
	public UserDAOMybatis userDAOMybatis() throws Exception {
		UserDAOMybatis userDAOMybatis = new UserDAOMybatis();
		userDAOMybatis.setSqlSession(sqlSession());// xml의 레퍼런스 ref를 가져오길 원한다
		return userDAOMybatis;
	}

	@Bean
	public UserInsertService userInsertService() throws Exception {
		UserInsertService userInsertService = new UserInsertService();
		userInsertService.setUserDTO(userDTO());// xml의 레퍼런스 ref를 가져오길 원한다
		userInsertService.setUserDAO(userDAOMybatis());// xml의 레퍼런스 ref를 가져오길 원한다
		return userInsertService;
	}

	@Bean
	public UserService userSelectService() throws Exception {
		UserSelectService userSelectService = new UserSelectService();
		userSelectService.setUserDAO(userDAOMybatis());// xml의 레퍼런스 ref를 가져오길 원한다

		return userSelectService;
	}

	@Bean
	public UserService userUpdateService() throws Exception {
		UserUpdateService userUpdateService = new UserUpdateService();
		userUpdateService.setUserDAO(userDAOMybatis());// xml의 레퍼런스 ref를 가져오길 원한다

		return userUpdateService;
	}

	@Bean
	public UserService userDeleteService() throws Exception {
		UserDeleteService userDeleteService = new UserDeleteService();
		userDeleteService.setUserDAO(userDAOMybatis());

		return userDeleteService;
	}
}
