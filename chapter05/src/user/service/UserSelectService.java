package user.service;

import java.util.List;

import lombok.Setter;
import user.bean.UserDTO;
import user.dao.UserDAO;

@Setter
public class UserSelectService implements UserService {
	//
	private UserDAO userDAO;
	
	@Override
	public void execute() {
		//한줄한줄은 UserDTO에 담는다 List에 담아서 UserDTO로 보낸다
		List<UserDTO> list = userDAO.getUserList();
		//UserDTO에 보낸 1인정보 포문으로 뿌리기
		for(UserDTO userDTO : list) {
			System.out.println(userDTO.getName()+"\t" + userDTO.getId()+"\t" + userDTO.getPwd());
		}

	}

}
