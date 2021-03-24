package user.service;

import java.util.Scanner;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import lombok.Setter;
import user.bean.UserDTO;
import user.dao.UserDAO;

@Setter
//@Service
@ComponentScan("user.conf")
public class UserInsertService implements UserService {
	//@Autowired
	private UserDTO userDTO;
	private UserDAO userDAO;
	//setter의 setUserDTO가 xml의 insertService의 name userDTO와 같은것이다
	//ref는 setter의 UserDTO userDTO 의 userDTO 이다
	
	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		
		//데이터
		System.out.println("이름 입력 : ");
		String name = scan.next();
		System.out.println("아이디 입력 : ");
		String id = scan.next();
		System.out.println("비밀번호 입력 : ");
		String pwd = scan.next();
		
		userDTO.setName(name);
		userDTO.setId(id);
		userDTO.setPwd(pwd);
		
		//DB
		userDAO.write(userDTO); //userDTO 들고 건나간다
		
		//응답
		System.out.println("데이터를 저장하였습니다.");
	}

}
