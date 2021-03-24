package user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import user.bean.UserDTO;

/*
// 내가 직접  JdbcTemplate bean으로 생성하지 않겠다
public class UserDAOImpl implements UserDAO {
	//jar에 포함되어 있어서 그냥 생성됨. 얘들이 DB관련 처리 대신 다 해줌.
	//그러므로 ds도 내장되어 있음.
	// 예전 DAO에서 하던 getConnection();
	// pstmt = conn.prepareStatement(sql) 등등을 템플릿클래스에서 해준다
	// 내가(UserDAOImpl) 가 DB로 보내는것이 아니라 템플릿이 해주는것이다(반장님)
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void write(UserDTO userDTO) {
		String sql = "insert into usertable values(?,?,?)";
		jdbcTemplate.update(sql, userDTO.getName(), userDTO.getId(), userDTO.getPwd());
	}

}
*/

//
public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements UserDAO {
	//NamedParameterJdbcDaoSupport 클래스에서 제공하고있는 메소드 getJdbcTemplate() 이다
	//xml template 부분 안해줘도 된다
	// JdbcTemplate은 NamedParameterJdbcDaoSupport 의 부모이기때문에 둘다처리가된다
	@Override
	public void write(UserDTO userDTO) {
		//String sql = "insert into usertable values(?,?,?)";
		//getJdbcTemplate().update(sql, userDTO.getName(), userDTO.getId(), userDTO.getPwd());
		
		//nameparameter를 이용한 데이터주입
		//?로 보내는것보다 구분하기 편하다
		String sql = "insert into usertable values(:name,:id,:pwd)";
		//맵에 넣어서 보낸다
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", userDTO.getName());
		map.put("id", userDTO.getId());
		map.put("pwd", userDTO.getPwd());
		getNamedParameterJdbcTemplate().update(sql, map);
		
	}

	@Override
	public List<UserDTO> getUserList() {
		String sql = "select * from usertable";
		//rowMapper는  한줄을 (행)을 뽑아내는 명령어? 이다
		//rowMapper 는 인터페이스라서 직접적으로 new 못한다 그래서 자식클래스로 들어간다
		//어떤클래스와 매핑할건지 알려준다 그럼 알아서 매핑해서 리스트에 담아간다
		//<UserDTO> 제너릭 걸어주면 노란줄 없어지지만 없어도 상관없다
		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<UserDTO>(UserDTO.class)); //UserDTO 와 오라클 컬럼 한줄과 메핑하라는 뜻 제너릭은 별 의미 없음
	}	//                      여러개라 query
	
	public Map<String, Object> getUser(String id){
		String sql = "select * from usertable where id=:id";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
	
		try { // 트라이 캐치 안잡아주면 리턴 널 에러뜬다
			map = getNamedParameterJdbcTemplate().queryForMap(sql, map);
		
		}catch(EmptyResultDataAccessException ex){
		   	map = null;
		}
		
		return map;
	}
/*
	@Override
	public UserDTO getUser(String id) {
		String sql = "select * from usertable where id=:id";
		
		try { // 트라이 캐치 안잡아주면 리턴 널 에러뜬다
		return getJdbcTemplate().queryForObject(// 한사람의 데이터라 queryForObject
				sql, 
				new BeanPropertyRowMapper<UserDTO>(UserDTO.class),
				id); // 아이디만 보내는거라 id 추가
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
*/


	@Override
	public void modify(Map<String, Object> map) {
		String sql = "update usertable set name=:name, pwd=:pwd where id=:id";
		getNamedParameterJdbcTemplate().update(sql, map);
	}

	@Override
	public void delete(String id) {
		String sql = "delete usertable where id=:id";
		getJdbcTemplate().update(sql, id);
	}
	
	//찾는 정보 갯수 알수있는 메소드 하지만 데이터출력이 안되서 쓸모없다
	@Override
	public int getUserCount(String id) {
		String sql = "select count(*) from usertable where id=:id";
		return getJdbcTemplate().queryForObject(
				sql, 
				Integer.class,
				id);
	}
	
}