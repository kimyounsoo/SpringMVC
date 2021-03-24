package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller // Controller 끼리는 url이 겹치면 에러난다
public class HelloController {// 해깔리지않게 url 주소와 메소드이름을 똑같게 많이쓴다 안해도 되긴한다
	@RequestMapping(value="/hello.do", method=RequestMethod.GET) //주소에 /hello 만 치면 get방식으로 스프링에 콜백메소드로 불러들인다
	public ModelAndView hello() { //사용자 마음데로 만든 콜백 메소드 주소의 값을 /hello 로 get방식으로 요청할때 불러온다 spring의 가장 큰 특징
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", "Hello Spring!!"); // 앞에 이름 뒤에 값
		mav.setViewName("hello");  // .jsp 라고 절대 쓰면안된다 .xml 로 보낼수도 있고 .html 로 보낼ㄷ수도 있기때문에 view 의 "이름" 만 써준다
		return mav;
	
	}


}