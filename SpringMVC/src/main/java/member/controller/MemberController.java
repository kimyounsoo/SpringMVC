package member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;
import member.service.MemberService;

@Controller
@RequestMapping(value="member") // / 빼도@RequestMapping 어노테이션이 알아서 해준다
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="loginForm", method=RequestMethod.GET)
	public String loginForm() {
		return "/member/loginForm";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody 
	public String login(@RequestParam Map<String, String> map, HttpSession session) { // 어차피 언젠가는 map으로 묶어야 보낼수있어서 여기서 맵으로 묶었다
		return memberService.login(map, session); // 맵에실어서 리턴값 문자열을 가지고 간다
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession session) { //데이터는 넘길게 없지만 세션은 넘겨야한다
		session.invalidate();// 세션 끊어버리는 작업
		return "/index"; // 로그아웃에 머물러 있으되 화면만 바뀌는 방식
	}
	
	@RequestMapping(value="writeForm", method=RequestMethod.GET)
	public String writeForm() {
		return "/member/writeForm";
	}
	
	@RequestMapping(value="write", method=RequestMethod.POST)
	public String write(@ModelAttribute MemberDTO memberDTO, Model model) {
		int su = memberService.write(memberDTO);
		
		model.addAttribute("su", su);
		model.addAttribute("display", "/member/write.jsp");
		return "/index";
	}
	
	@RequestMapping(value="/checkId", 
			produces = "application/String;charset=UTF-8",
			method=RequestMethod.POST)
	public @ResponseBody String checkId(String id) { // 여기도@ResponseBody 꼭 써줘야 한다 viewResolve의 영향을 받지않겠다(jsp로 가지않고 보낸곳으로 다시 돌아가게하는 명령어)
		String result = memberService.checkId(id);
		return result;
		
	}
	@RequestMapping(value="checkPost", method=RequestMethod.GET)
	public String checkPost() {
		return "/member/checkPost";
	}
	
	@RequestMapping(value="checkPostSearch", method=RequestMethod.POST)
	public ModelAndView checkPostSearch(@RequestParam Map<String, String> map) {
		List<ZipcodeDTO> list = memberService.checkPostSearch(map);
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("jsonView"); // 이건 jsonView.jsp 를 가려고 할텐데 그러면 안된다 jsonView bean의 이름을 찾아가게끔 설정해준다 suffix bean으로 가지 못하게 한다
		return mav;					//servlet-context.xml의 beanNameViewResolver 같은이름의 bean을 찾아가게 해주는 bean이다
	}
	
	@RequestMapping(value="modifyForm", method=RequestMethod.GET)
	public String modifyForm(HttpSession session, Model model) { // 수정은 세션을 들고가서(로그인상태) 그세션 에있는 정보를 뿌려주는것이라 세션도 가져간다, model 잡아서 담아간다
		String id = (String) session.getAttribute("memId");
		MemberDTO memberDTO = memberService.getMember(id); // 한사람분량의 데이터가 넘어온다
		
		model.addAttribute("memberDTO", memberDTO); //addAttribute 세션보내는 명령어 앞에서 배움
		model.addAttribute("display", "/member/modifyForm.jsp");
		return "/index";
		
	}
	
	@RequestMapping(value="modify", method=RequestMethod.POST)
	@ResponseBody
	public void modify(@ModelAttribute MemberDTO memberDTO) {
		memberService.modify(memberDTO);
	}
}
