package board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;
	@Autowired // MemberController 에서는 session을 Service 로 던져줬다 session 은Controller 에서 밖에 못얻는다고 한다 그래서 여기서는 
	private HttpSession session;// 여기서 필드잡고 @Autowired 해서 얻어왔다 member와 두가지 방법
	@Autowired
	private BoardPaging boardPaging; // memberBean 은 생성할게없어서 root-context에 안만들었지만 이건 만들어줘야한다
	
	@Override
	public void boardWrite(Map<String, String> map) { // 글쓰기는 로그인했을때만 글을 쓸수 있으므로 세션을 가져와야한다
		map.put("id", (String)session.getAttribute("memId"));
		map.put("name", (String)session.getAttribute("memName"));
		map.put("email", (String)session.getAttribute("memEmail"));
		
		boardDAO.boardWrite(map);		
	}


	@Override
	public List<BoardDTO> getBoardList(String pg) {
		//1페이지당 5개씩
		int endNum = Integer.parseInt(pg)*5;
		int startNum = endNum-4;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<BoardDTO> list = boardDAO.getBoardList(map);
		return list;
	}
	
	@Override
	public List<BoardDTO> getBoardSearch(Map<String, String> map) {
		//1페이지당 5개씩
		int endNum = Integer.parseInt(map.get("pg"))*5;
		int startNum = endNum-4;
		
		//이미 맵이 있기때문에 위처럼 맵생성해서 담을필요없이 그냥담으면 된다
		//map 에는 pg, searchType, keyword, startNum, endNum
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		
		return boardDAO.getBoardSearch(map);
	}

	@Override
	public BoardDTO getBoard(String seq) {
		return boardDAO.getBoard(seq);
	}
	

	@Override
	public void hitUpdate(String seq) {
		boardDAO.hitUpdate(seq);
	}

	@Override
	public BoardPaging boardPaging(String pg) {
		int totalA = boardDAO.getTotalA();//총글수

		boardPaging.setCurrentPage(Integer.parseInt(pg));//pg는 String 으로 넘어오는데 CurrentPage는 인트형이라 형변 필요
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		return boardPaging;
	}
	
	@Override
	public BoardPaging boardPaging(Map<String, String> map) {
		int totalA = boardDAO.getBoardSearchTotalA(map);//총글수 - 둘다 총글수 이지만 맵퍼 파일은  getTotalA 와 이름을 같게하면 안된다
														// searchType, keyword 가지고 가야한다
		boardPaging.setCurrentPage(Integer.parseInt(map.get("pg")));//pg는 String 으로 넘어오는데 CurrentPage는 인트형이라 형변 필요 
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		return boardPaging;
	}


	@Override
	public void boardModify(Map<String, String> map) {
		boardDAO.boardModify(map);
	}

	@Override
	public void boardDelete(String seq) {
		boardDAO.boardDelete(seq);
	}

	@Override
	public void boardReply(Map<String, String> map) {
		//원글
		BoardDTO pDTO = boardDAO.getBoard(map.get("pseq"));
		
		//map안에는 pseq, suject, content있다...추가 하자
		map.put("id", (String)session.getAttribute("memId"));
		map.put("name", (String)session.getAttribute("memName"));
		map.put("email", (String)session.getAttribute("memEmail"));
		map.put("ref", pDTO.getRef()+""); //ref = 원글ref
		map.put("lev", pDTO.getLev()+1 +""); //lev = 원글lev + 1
		map.put("step", pDTO.getStep()+1 +""); //step = 원글step + 1
		
		boardDAO.boardReply(map);
	}


}
