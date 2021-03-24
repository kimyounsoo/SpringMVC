package user.dao;

import java.util.List;
import java.util.Map;

import user.bean.UserDTO;

public interface UserDAO {
	//맨처음에 public 붙여줘야한다
	public void write(UserDTO userDTO);

	public List<UserDTO> getUserList();

	//public UserDTO getUser(String id);
	public Map<String, Object> getUser(String id);

	public int getUserCount(String id);

	public void modify(Map<String, Object> map);

	public void delete(String id);

}
