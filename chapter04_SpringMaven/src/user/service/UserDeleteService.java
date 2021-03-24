package user.service;

import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.dao.UserDAO;

@Service("userDeleteService")
public class UserDeleteService implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);

		// 데이터
		System.out.println("삭제할 아이디 입력 : ");
		String id = scan.next();

		// DB 한사람의 id 데이터를 보낸다 일단 찾는데이터 보내고 아래에서 수정하는데이터 다시보낸다
		Map<String, Object> map = userDAO.getUser(id);
		if (map==null) {
			System.out.println("찾고자하는 아이디가 없습니다.");
			return;
		}
		// DB
		userDAO.delete(id);

		// 응답
		System.out.println("데이터를 삭제하였습니다");
	}

}
