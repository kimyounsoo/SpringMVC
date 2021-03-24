package user.service;

import java.util.Map;
import java.util.Scanner;

import lombok.Setter;
import user.dao.UserDAO;

public class UserUpdateService implements UserService {
	@Setter
	private UserDAO userDAO;

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("수정할 아이디 입력 : ");
		String id = scan.next();
		
		//DB 한사람의 id 데이터를 보낸다 일단 찾는데이터 보내고 아래에서 수정하는데이터 다시보낸다
//		UserDTO userDTO = userDAO.getUser(id);
//		if(userDTO==null) {
//			System.out.println("찾고자 하는 아이디 없음");
//			return;
//		}
		//-----------------------------
		//맵으로 userDAO 에 getUser 메소드로 id 를 실어서 보낸다
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
		System.out.print("수정할 이름 : ");
		String name = scan.next();
		System.out.print("수정할 비밀번호 : ");
		String pwd = scan.next();
		
		map.put("id", id);
		map.put("name", name);
		map.put("pwd", pwd);
		
		//DB 수정한 정보를 실어보내야해서 map으로 보낸다
		userDAO.modify(map);
		
		System.out.println("데이터를 수정하였습니다");

	}

}
