package sample05;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Setter;



//@Setter

//@Component("spring.conf")
@Component("sungJukInput") // 로 생성된 bean 중에서SungJukDTO2 를 찾아서 주소값을 가져와서 매핑
@Scope("prototype")
public class SungJukInput implements SungJuk {
	// Setter 없애고 @Autowired 걸어준다 16번째줄의 이유
	@Autowired
	private SungJukDTO2 sungJukDTO2;
	//부모인 인터페이스 (List) 의 많은 자식들이 있는데 그중에 하나인 list(객체명)을 @Qualifier 에 써준다 spring.conf 에  47번줄 list와 같은 list이다
	//같은 자료형의 애를 따라간후  id(객체명) 이 일치하는 애를 찾아가서 객체를 들고온다.
	@Qualifier("list")
	@Autowired
	private List<SungJukDTO2> list;

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.print("이름 입력 : ");
		String name = scan.next();
		System.out.print("국어점수 입력 : ");
		int kor = scan.nextInt();
		System.out.print("영어점수 입력 : ");
		int eng = scan.nextInt();
		System.out.print("수학점수 입력 : ");
		int math = scan.nextInt();
		
		int tot = kor + eng + math;
	    double avg = tot/3.0;
		
		sungJukDTO2.setName(name);
		sungJukDTO2.setKor(kor);
		sungJukDTO2.setEng(eng);
		sungJukDTO2.setMath(math);
		sungJukDTO2.setTot(tot);
		sungJukDTO2.setAvg(avg);
		
		list.add(sungJukDTO2);
		
		System.out.println(name + "님의 데이터를 입력 하였습니다.");
	}

}
