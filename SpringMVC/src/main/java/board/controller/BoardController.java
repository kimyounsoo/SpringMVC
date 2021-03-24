package board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.service.BoardService;

@Controller
@RequestMapping(value="board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="boardWriteForm", method=RequestMethod.GET)
	public String boardWriteForm(Model model) {
		model.addAttribute("display", "/board/boardWriteForm.jsp");
		return "/index";
	}
	
	@RequestMapping(value="boardWrite", method=RequestMethod.POST)
	@ResponseBody //@ResponseBody 해주면 리졸버 안찾고 그냥간다
	public void boardWrite(@RequestParam Map<String, String> map) {
		boardService.boardWrite(map);
	}
	
	@RequestMapping(value="boardList", method=RequestMethod.GET) // menu에서 boardList는 무조건 1페이지(?pg=1)로 가야하는데  을 없애서 404에러가 뜨는데 그걸 방지하기위해인자값없이도 가라 (required=false) 해주고 디폴트 값 1 도 써줬다  
	public String boardList(@RequestParam(required=false, defaultValue="1") String pg, Model model) {
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/board/boardList.jsp");
		return "/index";
	}
	
	@RequestMapping(value="getBoardList", method=RequestMethod.POST)
	public ModelAndView getBoardList(@RequestParam(required=false, defaultValue="1") String pg,
										HttpSession session,// 글은 로그인 해야만 볼수 있기 떄문에 세션실어준다
										HttpServletResponse response) { //여기엔 response 가 없기때문에 같이 실어준다
		
		List<BoardDTO> list = boardService.getBoardList(pg);

		String memId = (String)session.getAttribute("memId");
									  
		//조회수 - 새로고침 방지 - 여기에 세션까지 다 가지고 있기때문에 이곳에 작성한다
		if(session.getAttribute("memId") != null) {
    		Cookie cookie = new Cookie("memHit", "0");//생성
    		cookie.setMaxAge(30*60);//초 단위 생존기간
    		cookie.setPath("/"); //모든 경로에서 접근 가능 하도록 설정
    		response.addCookie(cookie);//클라이언트에게 보내기
    	}
		
		//페이징처리
		//boardPaging을 들고 어디로갈까? boardList.js 의 sucees의(data) 로간다 boardList.jsp 로가는것이 아니다 
		//boardList.js 의 url 타고 boardcontrller의 getboardList 로가서 페이징처리하고 return값 mav 가지고 boardList.js 의 sucees의(data) 로간다
		BoardPaging boardPaging = boardService.boardPaging(pg);
		
		ModelAndView mav = new ModelAndView();// ModelAndView 자주쓸거면 필드에 잡고 써도 무관하다
		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.addObject("memId", memId);
		mav.addObject("boardPaging", boardPaging);
		mav.setViewName("jsonView"); // 이건 jsonView.jsp 를 가려고 할텐데 그러면 안된다 jsonView bean의 이름을 찾아가게끔 설정해준다 suffix bean으로 가지 못하게 한다
		return mav;					//servlet-context.xml의 beanNameViewResolver 같은이름의 bean을 찾아가게 해주는 bean이다
	}								// 내부적으로 jason 객체로 바꿔서 레턴해준다
									// 만약 controller의setViewName 이 jsonView 이게 아니고다른이름이면 suffix bean의 .jsp 가 리턴된다

	@RequestMapping(value="getBoardSearch", method=RequestMethod.POST)
	public ModelAndView getBoardSearch(@RequestParam Map<String,String> map) { // 데이터가 3개라 map으로 보낸다
		List<BoardDTO> list = boardService.getBoardSearch(map); //pg, searchType, keyword
		
		
		//페이징 처리 getBoardList 의 boardPaging이름은 같지만 매개변수(list는 String 이건 map)가 다른 것을 오버로드 라고한다
		BoardPaging boardPaging = boardService.boardPaging(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", map.get("pg")); // pg 도 보내줘야한다
		mav.addObject("list", list); // list 실어줘야 잘간다 강사님 실수
		mav.addObject("boardPaging", boardPaging);
		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
	
	@RequestMapping(value="boardView",method=RequestMethod.GET) // boardView 없이 seq만 보내면 되기 때문에 map으로 보낼필요 없다
	public String boardView(@RequestParam String seq,
							@RequestParam(required=false,defaultValue="1")String pg,
							Model model) {
		model.addAttribute("seq",seq);
		model.addAttribute("pg",pg);
		model.addAttribute("display","/board/boardView.jsp");
		 return "/index";
	}
	
	@RequestMapping(value="getBoard", method=RequestMethod.POST)
	public ModelAndView getBoard(@RequestParam String seq,
								@CookieValue(value="memHit", required=false) Cookie cookie, // 쿠키는 있을때도있고 없을때도 있기때문에
								HttpServletResponse response, // 여기도 response 잡아온다
								HttpSession session) { // 글수정 여부 는 자신의 글만 수정 할수 있기 때문에 얘도 세션 가지고가야한다
		
		//조회수 - 새로고침 방지 이클립스에서는 모든 쿠키가져와서 포문 돌려서 다가져오는 방식 이었고 스프링은@CookieValue(value="memHit", required=false) Cookie cookie로 원하는 쿠키의 값을가져왔다(스프링은 어노테이션 기능으로 가능)
		if(cookie != null) {
			boardService.hitUpdate(seq); //조회수 증가
			cookie.setMaxAge(0); //쿠키 삭제
			cookie.setPath("/"); //모든 경로에서 삭제 되었음을 알림
			response.addCookie(cookie); //쿠키 삭제된걸 클라이언트에게 보내주기.
		}
		BoardDTO boardDTO = boardService.getBoard(seq); // 이걸 쿠키생성 아래다 입력해야 에러가 없다
		
		String memId = (String)session.getAttribute("memId");
		
		ModelAndView mav = new ModelAndView(); // 아이디 세션도 가지고 간다(memId)
		mav.addObject("boardDTO",boardDTO);
		mav.addObject("memId", memId);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="boardModifyForm", method=RequestMethod.POST)
	public String boardModifyForm(@RequestParam String seq,
								  @RequestParam String pg,
								  Model model) {
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/board/boardModifyForm.jsp");
		return "/index";
	}
	

	
	@RequestMapping(value="boardModify", method=RequestMethod.POST)
	@ResponseBody
	public void boardModify(@RequestParam Map<String, String> map) {
		boardService.boardModify(map);		
	}
	
	@RequestMapping(value="boardDeleteForm", method=RequestMethod.POST)
	public String boardDeleteForm(@RequestParam String seq, Model model) {
		//boardService.boardDelete(seq);
		
		model.addAttribute("seq", seq);
		model.addAttribute("display", "/board/boardDelete.jsp");
		return "/index";
	}
	
	@RequestMapping(value="boardDelete", method=RequestMethod.POST)
	@ResponseBody
	public void boardDelete(@RequestParam String seq, Model model) {
		boardService.boardDelete(seq);
	}
	
	@RequestMapping(value="boardReplyForm", method=RequestMethod.POST)
	public String boardReplyForm(@RequestParam String seq,
								 @RequestParam String pg,
								 Model model) {
		model.addAttribute("pseq",seq); // 원글번호, 답글은 원글번호(pseq)가 글번호(seq)가 된다 
		model.addAttribute("pg",pg);
		model.addAttribute("display", "/board/boardReplyForm.jsp");
		return "/index";
		
	}
	
	
	
	@RequestMapping(value="boardReply", method=RequestMethod.POST)
	@ResponseBody
	public void boardReply(@RequestParam Map<String, String> map) {
		boardService.boardReply(map);
	}
	
}

//boardList.js -> BoardController -> BoardServiceImpl -> BoardDAOMybatis -> boardMapper.xml
//boardMapper.xml -> BoardDAOMybatis -> BoardServiceImpl BoardController -> boardList.js의 (data) 로 들어간다

