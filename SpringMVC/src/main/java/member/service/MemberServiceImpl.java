package member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;
import member.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Override
	public String login(Map<String, String> map, HttpSession session) {
		MemberDTO memberDTO = memberDAO.login(map);
		if(memberDTO == null) { // 로그인 해당아이디가 있다 / 없다, 리턴 스트링값을 기다리고있기때문에
			return "fail";
		}else {
			// 당연히 여기도 로그인 이니까 세션 잡아가야한다(성공시)
			session.setAttribute("memName", memberDTO.getName());
			session.setAttribute("memId", map.get("id"));
			session.setAttribute("memEmail", memberDTO.getEmail1()+"@"+memberDTO.getEmail2());
			
			return "success";
		}
	}
	@Override
	public int write(MemberDTO memberDTO) {
		return memberDAO.write(memberDTO);
	}
	@Override
	public String checkId(String id) {
		MemberDTO memberDTO = memberDAO.checkId(id);
		
		if(memberDTO == null) // return 값은 ajax의 data == 값 값에 따라서 써준다
			return "non_exist";
		else
			return "exist";
	}
	@Override
	public List<ZipcodeDTO> checkPostSearch(Map<String, String> map) {
		return memberDAO.checkPostSearch(map);
	}
	@Override
	public MemberDTO getMember(String id) {
		return memberDAO.getMember(id);
	}
	@Override
	public void modify(MemberDTO memberDTO) {
		memberDAO.modify(memberDTO);
	}
	
}
