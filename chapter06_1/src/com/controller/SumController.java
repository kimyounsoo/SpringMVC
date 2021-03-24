package com.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.SumDTO;

@Controller
public class SumController {
//	@RequestMapping(value = "input.do", method=RequestMethod.GET)
//	public ModelAndView input() {
//		ModelAndView mav = new ModelAndView();
//		// 값을 실어보내려고 온것이 아니기 때문에
//		//mav.setViewName("input"); // jsp의 이름을 줄려고온것이지 chapter06처럼 addObject하러 온것이 아니다
//		mav.setViewName("/sum/input");
//		return mav;
//	}

	@RequestMapping(value = "input.do", method = RequestMethod.GET)
	// 리턴 타입이 String이면 단순 문자열이 아니라 뷰이름으로 사용된다
	// 뷰이름이 아니라 실제 문자열로 리턴하고 싶을 때는 @ResponseBody를 붙인다
	public String input() { // String은 단순 문자열이 아니라 데이터의 파일명으로 들어간다
		return "/sum/input";

	}

	// ---------------------
//	@RequestMapping(value="result.do", method=RequestMethod.GET)
//	public ModelAndView result() {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/sum/result"); //spring이 자동완성 해주므로 경로가 아닌 이름만 적으면 됨
//	      return mav;
//	}

//	@RequestMapping(value="result.do", method=RequestMethod.GET)
//	public ModelAndView result(@RequestParam int x, @RequestParam int y) { //@RequestParam 은 요청들어오는 데이터를 직접 받는것이다 
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("x", x);
//		mav.addObject("y", y);
//		mav.addObject("sum", x+y);
//		mav.setViewName("/sum/result"); //spring이 자동완성 해주므로 경로가 아닌 이름만 적으면 됨
//	      return mav;
//	}
	
	//ModelAndView로 보내는방식
//	@RequestMapping(value="result.do", method=RequestMethod.GET)
//	public ModelAndView result(// 바로위의방식은 x에 값을 입력하지않으면 400에러가뜬다
//			@RequestParam(required=false, defaultValue = "0")String x, // 파라미터값이 안들어오면 400에러가 뜨는데 그걸 방지하기 위함이다
//			@RequestParam(required=false, defaultValue = "0")String y) { //String으로 받는것이 좋다 int형은 exception 에러를 발생할수있어서 이다 
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("x", x);
//		mav.addObject("y", y);
//		mav.addObject("sum", Integer.parseInt(x)+Integer.parseInt(y));
//		mav.setViewName("/sum/result"); //spring이 자동완성 해주므로 경로가 아닌 이름만 적으면 됨
//	      return mav;
//	}
	
	//모델 맵으로 보내는 방식
//	@RequestMapping(value = "result.do", method = RequestMethod.GET)
//	//String , String 잡고
//	public String result(@RequestParam Map<String, String> map, ModelMap modelMap) { // String은 단순 문자열이 아니라 데이터의 파일명으로 들어간다
//		//정수형으로 바꾼후에
//		int x = Integer.parseInt(map.get("x"));
//		int y = Integer.parseInt(map.get("y"));
//		//계산한다
//		int sum = x+y;
//		modelMap.put("x", map.get("x"));
//		modelMap.put("y", map.get("y"));
//		modelMap.put("sum", sum);
//		return "/sum/result";
//	}
	
	//그냥 모델로 보내는 방식
	@RequestMapping(value="result.do", method=RequestMethod.GET)
	public String result(@ModelAttribute SumDTO sumDTO, Model model) { // String은 단순 문자열이 아니라 데이터의 파일명으로 들어간다
		model.addAttribute("x",sumDTO.getX());						   // model은 나가는 방식??
		model.addAttribute("y",sumDTO.getY());
		model.addAttribute("sum", sumDTO.getX() + sumDTO.getY());
		
		//sumDTO 통을 보내는방식 result.jsp에서 고쳐줘야할 부분이 생긴다
		/* model.addAttribute("sumDTO", sumDTO); */
		
	      return "/sum/result";
	}
}
