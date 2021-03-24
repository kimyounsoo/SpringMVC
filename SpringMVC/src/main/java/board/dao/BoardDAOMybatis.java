package board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import board.bean.BoardDTO;

@Repository
@Transactional
public class BoardDAOMybatis implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void boardWrite(Map<String, String> map) {
		sqlSession.insert("boardSQL.boardWrite", map);
	}

	@Override
	public List<BoardDTO> getBoardList(Map<String, Integer> map) {
		return sqlSession.selectList("boardSQL.getBoardList", map);
	}

	@Override
	public List<BoardDTO> getBoardSearch(Map<String, String> map) {
		return sqlSession.selectList("boardSQL.getBoardSearch", map);
	}
	
	@Override
	public BoardDTO getBoard(String seq) { // seq를 String 으로 받았기 때문에 형변환
		return sqlSession.selectOne("boardSQL.getBoard", Integer.parseInt(seq));
	}

	@Override
	public void hitUpdate(String seq) {// seq를 String 으로 받았기 때문에 형변환
		sqlSession.update("boardSQL.hitUpdate", Integer.parseInt(seq));
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("boardSQL.getTotalA");
	}
	
	@Override
	public int getBoardSearchTotalA(Map<String, String> map) {
		return sqlSession.selectOne("boardSQL.getBoardSearchTotalA", map);
	}

	@Override
	public void boardModify(Map<String, String> map) {
		sqlSession.update("boardSQL.boardModify", map);
	}

	@Override
	public void boardDelete(String seq) {// seq를 String 으로 받았기 때문에 형변환
		sqlSession.delete("boardSQL.boardDelete", Integer.parseInt(seq));
	}

	@Override
	public void boardReply(Map<String, String> map) {
		sqlSession.insert("boardSQL.boardReply", map);
	}
}










