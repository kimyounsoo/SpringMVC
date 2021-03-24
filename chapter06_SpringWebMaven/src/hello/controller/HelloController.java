package hello.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
//servlet-contxt.xml에 bean설정만해주면 @ResponseBody 해줬기 때문에 뷰 주소 찾지않고 화면에 문자열 그대로 뿌려준다
public class HelloController {
//	@RequestMapping(value="/hello", method=RequestMethod.GET)
//	@ResponseBody public String hello() {
//		return "안녕하세요 JSON!!"; //StringHttpMessageConverter는 응답 메세지인 문자열(한글)을 인코딩 해준다(serlvet-context.xml)
//	}

	//제이슨뷰어 설치했음(jason Object 형식)
//	@RequestMapping(value="/hello", method=RequestMethod.GET)
//	@ResponseBody public JSONObject hello() {
//		JSONObject json = new JSONObject();
//		json.put("name", "hong");
//		json.put("age", 25);
//		return json;
//	}
	
	//map으로 보내는 형식
//	@RequestMapping(value="/hello", method=RequestMethod.GET)
//	@ResponseBody public Map<String, Object> hello() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("name", "홍길동");
//		map.put("age", 25);
//		return map;
//	}
	//jason 배열로 보내는 형식
//	@RequestMapping(value="/hello", method=RequestMethod.GET)
//	@ResponseBody public JSONArray hello() {
//		JSONArray array = new JSONArray();
//		
//		JSONObject temp = new JSONObject();
//		temp.put("name", "홍길동");
//		temp.put("age", 25);
//		array.add(0, temp); // temp 를 0번째 방에 넣어라
//		
//		temp = new JSONObject();
//		temp.put("name", "코난");
//		temp.put("age", 17);
//		array.add(1, temp);
//		
//		return array;
//		
//	}
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	@ResponseBody public JSONObject hello() {
		JSONArray array = new JSONArray();
		
		JSONObject temp = new JSONObject();
		temp.put("name", "홍길동");
		temp.put("age", 25);
		array.add(0, temp); // temp 를 0번째 방에 넣어라
		
		temp = new JSONObject();
		temp.put("name", "코난");
		temp.put("age", 17);
		array.add(1, temp);
		
		JSONObject json = new JSONObject();
		json.put("json", array);
		return json;
	}
	
}

