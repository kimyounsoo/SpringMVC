package user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import user.bean.UserDTO;

@Transactional //커밋 클로즈 자동으로 해주는 기능 어노테이션 안쓰는 방법도 있긴하지만 어려워서 생략
public class UserDAOMybatis implements UserDAO{
	//마이바티스는 sqlSession 이 꼭 필요하다 그러므로 만들어준다
	// xml에 다가도 property 해준다
	@Setter
	private SqlSession sqlSession;
	
	@Override
	public void write(UserDTO userDTO) {
		sqlSession.insert("userSQL.write",userDTO);
		
	}

	@Override
	public List<UserDTO> getUserList() {
		return sqlSession.selectList("userSQL.getUserList");// list로 했기떄문에 알아서 list로 리턴한다 신경쓰지말것
	}

	@Override
	public Map<String, Object> getUser(String id) {
		return sqlSession.selectOne("userSQL.getUser", id);
	}

	@Override
	public void modify(Map<String, Object> map) {
		sqlSession.update("userSQL.modify", map);
		
	}
	@Override
	public void delete(String id) {
		sqlSession.delete("userSQL.delete",id);
		
	}

}
