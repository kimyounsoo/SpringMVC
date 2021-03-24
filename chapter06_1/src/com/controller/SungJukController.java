package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bean.SungJukDTO;

@Controller
public class SungJukController {
	//value의 값을 sum 과 다르기때문에 /sungJuk/ 도 써줘야한다
	@RequestMapping(value = "/sungJuk/input.do", method = RequestMethod.GET)
	// 리턴 타입이 String이면 단순 문자열이 아니라 뷰이름으로 사용된다
	// 뷰이름이 아니라 실제 문자열로 리턴하고 싶을 때는 @ResponseBody를 붙인다
	public String sungJukInput() { // String은 단순 문자열이 아니라 데이터의 파일명으로 들어간다
		return "/sungJuk/input";

	}
	

	
	//그냥 모델로 보내는 방식
	@RequestMapping(value="/sungJuk/result.do", method=RequestMethod.POST)//web.xml 에서 한글처리 해주는 코드추가가 함정
	public String sungJukResult(@ModelAttribute SungJukDTO sungJukDTO, ModelMap modelMap) { // String은 단순 문자열이 아니라 데이터의 파일명으로 들어간다
		int tot = sungJukDTO.getKor() + sungJukDTO.getEng() + sungJukDTO.getMath();
		double avg = tot/3.0;
		sungJukDTO.setTot(tot);
		sungJukDTO.setAvg(avg);
		modelMap.addAttribute("sungJukDTO",sungJukDTO);						   // model은 나가는 방식 일 뿐이다??
	
			
			return "/sungJuk/result";
	}
	
}
