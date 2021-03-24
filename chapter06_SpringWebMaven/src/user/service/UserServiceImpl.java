package user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.dao.UserDAO;

@Service(value="userService") // @Service(value="userDAO") 이렇게 썻다가 개고생함
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public void write(UserDTO userDTO) {
		userDAO.write(userDTO);		
	}

	@Override
	public String checkId(String id) {
		UserDTO userDTO = userDAO.checkId(id);
		
		if(userDTO == null)
			return "사용 가능";
		else
			return "사용 불가능";
	}

	@Override
	public List<UserDTO> getUserList() {
		return userDAO.getUserList();
	}

	@Override
	public UserDTO getUser(String id) {
		return userDAO.getUser(id);
	}

	@Override
	public void modify(UserDTO userDTO) {
		userDAO.modify(userDTO);
		
	}


	@Override
	public void delete(String id) {
		userDAO.delete(id);
		
	}

	@Override
	public List<UserDTO> search(Map<String, String> map) {
		return userDAO.search(map);
	}

}

/*
userController의 userService.write() -> userServiceImpl의 userDAO.write() ->
userDAO.insert() -> userDAOMybatis의 insert() -> userMapper ->
DB 실행 후 다시 userController로 돌아와서 return /user/write -> write.jsp
*/









