package user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import user.bean.UserDTO;

@Repository(value = "userDAO")
@Transactional
public class UserDAOMybatis implements UserDAO {
	// mybatis 관련 lib 여기선 xml에 추가해줘야 SqlSession 쓸수있다
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void write(UserDTO userDTO) {
		sqlSession.insert("userSQL.write", userDTO);
	}

	@Override
	public UserDTO checkId(String id) {
		return sqlSession.selectOne("userSQL.checkId", id);
	}

	@Override
	public List<UserDTO> getUserList() {
		return sqlSession.selectList("userSQL.getUserList");
	}

	@Override
	public UserDTO getUser(String id) {
		return sqlSession.selectOne("userSQL.getUser", id);
	}

	@Override
	public void modify(UserDTO userDTO) {
		sqlSession.selectOne("userSQL.modify", userDTO);

	}

	@Override
	public void delete(String id) {
		sqlSession.delete("userSQL.delete", id);//selectOne 이 아니라 delete이다

	}

	@Override
	public List<UserDTO> search(Map<String, String> map) {
		return sqlSession.selectList("userSQL.search", map);
	}

}
