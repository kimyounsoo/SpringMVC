package user.service;

import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import lombok.Setter;
import user.bean.UserDTO;
import user.dao.UserDAO;

//@Service
@Setter
@ComponentScan("user.conf")
public class UserUpdateService implements UserService {
	//@Autowired
	private UserDAO userDAO;

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("수정할 아이디 입력 >> ");
		String id = scan.next();
		
		//DB
//		UserDTO userDTO = userDAO.getUser(id);
//		if(userDTO==null) {
//			System.out.println("찾고자 하는 아이디 없음");
//			return;
//		}
		//-----------------------------
		Map<String, Object> map = userDAO.getUser(id);
		if(map==null) {
			System.out.println("찾고자 하는 아이디 없음");
			return;
		}
		
		System.out.println();
		System.out.println(map.get("name")+"\t" +map.get("id")+"\t" +map.get("pwd"));
		//-----------------------------
		
//		int su = userDAO.getUserCount(id);
//		if(su==0) {
//			System.out.println("찾고자 하는 아이디 없음");
//			return;
//		}
		
		System.out.println();
		System.out.print("수정할 이름 >> ");
		String name = scan.next();
		System.out.print("수정할 비밀번호 >> ");
		String pwd = scan.next();
		
		map.put("id", id);
		map.put("name", name);
		map.put("pwd", pwd);
		
		userDAO.modify(map);
		
		System.out.println("데이터를 수정하였습니다");

	}

}









