package user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import user.bean.UserDTO;
import user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	//@RequestMapping(value="/user/writeForm", method=RequestMethod.GET)
	@RequestMapping(value="/writeForm", method=RequestMethod.GET)
	public String writeForm() {
		return "/user/writeForm";
	}
	
	//RquestParam 써서 데이터3개 받는다 3개의 데이터이기때문에
	// 또는 RequestParam Map으로
	//write는 스프링이 호출하는 콜백 메소드이다 -> 그러므로 UserDTO 를 생성해서 값을 받는다
	@RequestMapping(value="/write", method=RequestMethod.POST)
	@ResponseBody // viewResolve의 영향을 받지않겠다(jsp로 가지않고 보낸곳으로 다시 돌아가게하는 명령어) 뷰의주소가 아니다, 뷰로가지않게 하는 명령어 
	public void write(@ModelAttribute UserDTO userDTO) {//그래서 writeForm 에서 창이안넘어가고(회원등록시점 write창 안띄우고) alert창 바로띄운다 write.jsp는 삭제했다
			userService.write(userDTO);
		//String 이었을때는 문자열이 아니라 jsp 뷰이름을 뜻하는것인데 jsp로 보내지 않기위해 void 로변경해서 return 없앴다
		
	}
	
	@RequestMapping(value="/checkId", 
			produces = "application/String;charset=UTF-8",
			method=RequestMethod.POST)
	public @ResponseBody String checkId(String id) { // 여기도@ResponseBody 꼭 써줘야 한다 viewResolve의 영향을 받지않겠다(jsp로 가지않고 보낸곳으로 다시 돌아가게하는 명령어)
		String result = userService.checkId(id);
		return result;
		
	}
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list() {
		return "/user/list";
	
	//모델앤뷰 방식으로 넘김
//	public ModelAndView list() {
//		List<UserDTO> list = userService.getUserList();
//		
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("list", list);
//		mav.setViewName("/user/list");
//		return mav;
	}
	
	// ----------오리지날 방식------------
//	@RequestMapping(value="getUserList", method=RequestMethod.POST)
//	@ResponseBody // 이거 안해주면 ViewResolve 타고 가려고한다
//	public JSONObject getUserList(){
//		List<UserDTO> list = userService.getUserList(); // UserController에서  UserServiceImpl 로 넘어가는 시점은 이곳이다
//		System.out.println(list);
//		
//		JSONObject json = new JSONObject(); // JSON Object 만들기 {}
//		if(list != null) {
//			JSONArray array = new JSONArray();// JSON Array 만들어주기 []
//			
//			for(int i=0; i<list.size(); i++) {
//				UserDTO userDTO = list.get(i); // UserDTO 가 list 안에 들어있으므로 i 로 하나씩 꺼내준다
//				
//				JSONObject temp = new JSONObject(); // JOSON temp 만들기 {} 안에 리스트
//				temp.put("name", userDTO.getName());
//				temp.put("id", userDTO.getId());
//				temp.put("pwd", userDTO.getPwd());
//				
//				array.add(i, temp);
//			}//for
//			
//			json.put("list", array);
//		}
//		System.out.println(json);
//		return json;
//	}
	
	//Map 반환해도 @ResponseBody 때문에 dispatcher서블릿에서 MessageConverter가 JSON 형식으로 변환해서 $.ajax에게 데이터를 보내주는 것
//	@RequestMapping(value="getUserList", method=RequestMethod.POST)
//	@ResponseBody // 이거 안해주면 ViewResolve 타고 가려고한다
//	public Map getUserList(){
//		List<UserDTO> list = userService.getUserList(); // UserController에서  UserServiceImpl 로 넘어가는 시점은 이곳이다
//		
//		JSONArray array = JSONArray.fromObject(list);// 이렇게만쓰면 바로위의 if문 for 문의  데이터를 맵으로 넘기는 메소드이다
//		Map map = new HashMap();
//		map.put("list", array);
//		return map;
//	}
	@RequestMapping(value="getUserList", method=RequestMethod.POST)
	@ResponseBody // 이거 안해주면 ViewResolve 타고 가려고한다
	public ModelAndView getUserList(){
		List<UserDTO> list = userService.getUserList(); // UserController에서  UserServiceImpl 로 넘어가는 시점은 이곳이다
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("jsonView"); //servlet-context.xml의 이런 이름을 가진 bean을 찾아라 bean이름이 다르면(ex: jsonView2) null 값이 리턴되서 beanNameViewResolver도  null 이 되고 마지막 우선순위인 viewResolver로 간다
		return mav;
		// 1. dispatcher 를 호출하면 무조건servlet-context.xml viewResolver bean을 먼저 찾아간다 
		// 2. 그러면 .jsp로 가기때문에 이걸타고가면 안되서 beanNameViewResolver(우선순위 0으로 설정)를 작성해서 타고가게끔 만들어준다.(우선순위 0번으로 작성해놓음)
		// 3. beanNameViewResolver타고 jsonView의 MappingJackson2JsonView 으로간다.
		// 4. MappingJackson2JsonView 로 인해서 자동으로 맵핑이 일어난다.
		// 5. 그래서 자동으로 제이슨으로 바뀌어서 결과도 제이슨으로 돌아온다
									
	}								
	
	@RequestMapping(value="/modifyForm", method=RequestMethod.GET)
	public String modifyForm() {
		return "/user/modifyForm";
		
	}
	@RequestMapping(value="/getUser", method=RequestMethod.POST)
	@ResponseBody // 이거 안해주면 ViewResolve 타고 가려고한다 
	public JSONObject getUser(@RequestParam String id) { // id 는 modifyForm 의 data 'id'이다 void 아니고 JSONObject modifyForm 의 url 타고온다
		UserDTO userDTO = userService.getUser(id);
		
		JSONObject json = new JSONObject(); // 중괄호인 제이슨 오브젝트
		if(userDTO != null) { // 아무것도 없지 않으면 데이터를 넘긴다 제이슨은 아무것도 없을때 {} 나온다 이미 new 했기 때문에 null이 아니다
			json.put("name", userDTO.getName());
			json.put("id", userDTO.getId());
			json.put("pwd", userDTO.getPwd());
		}
		
		return json; // modifyForm의 result로 간다
		
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	@ResponseBody
	public void modify(@ModelAttribute UserDTO userDTO) {
		userService.modify(userDTO);
	}
	
	@RequestMapping(value="/deleteForm", method=RequestMethod.GET) // GET인 이유는 index.jsp에서 링크걸고 들어오기때문에 GET방식으로 요청이 들어온다
	public String deleteForm() {
		return "/user/deleteForm"; // 우선순위 0인 beanNameViewResolver로 가지만 jsonView 이름이 맞지 않기때문에 null 값이 리턴되면서 viewResolver로 간다
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public void delete(@RequestParam String id) {
		userService.delete(id);
	}
	
	//------------오리지날 방법--------------------
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody // 응답으로 가는쪽 제이슨이 가는쪽 보내는쪽
	//public JSONObject search(@RequestParam String searchOption, @RequestParam String searchText) { // 문자열이 아닌 제이슨형태로 받기때문에 이건 안된다
	// 위의 방식을 쓰고싶다면 data를 searchOption 으로 보내면 된다(list.jsp의 data)
	public JSONObject search(@RequestBody Map<String, String> map) {//제이슨으로 받을수 없어서 @RequestBody써줘야 제이슨으로 받는다  map 으로 받는다 @RequestBody는 받는쪽
		System.out.println("searchOption="+ map.get("searchOption"));
		
		//List<UserDTO> list = userService.search(searchOption, searchText); // 두개의 데이터를 가지고 가야한다 그것말고는 getUserList와 동일 
		List<UserDTO> list = userService.search(map);// map으로 받았으니 map 으로 보낸다
		System.out.println(list);
		
		JSONObject json = new JSONObject(); // JSON Object 만들기 {}
		if(list != null) {
			JSONArray array = new JSONArray();// JSON Array 만들어주기 []
			
			for(int i=0; i<list.size(); i++) {
				UserDTO userDTO = list.get(i); // UserDTO 가 list 안에 들어있으므로 i 로 하나씩 꺼내준다
				
				JSONObject temp = new JSONObject(); // JOSON temp 만들기 {} 안에 리스트
				temp.put("name", userDTO.getName());
				temp.put("id", userDTO.getId());
				temp.put("pwd", userDTO.getPwd());
				
				array.add(i, temp);
			}//for
			
			json.put("list", array);
		}
		System.out.println(json);
		return json;
	
	}
}

//제이슨은 객체로 못받아서 UserDTO(객체) -------> JSON 변환 해야한다
//UserDTO(name, id, pwd)   이 중괄호는 제이슨 오브젝트   {'name': '홍길동' , 'id': 'hong', 'pwd': '111'}

//한글이 깨지는 이유 result 값을java로보내고 javascript(user.js)가 값을 받는다 충돌이난다
//여기서(UserController) 에서 한글처리 해주고 보내야한다(38번줄 한글처리코드)

//@ResponseBody 반환되는 문자열이 viewResolver 또는 MessageConverter

//stringify 쓰면 ContentType이랑 RequestBody 써줘야하고, stringify 안쓰면 RequestParam 만 써서 받아주는것

