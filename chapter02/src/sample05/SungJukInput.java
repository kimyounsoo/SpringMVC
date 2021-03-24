package sample05;

import java.util.List;
import java.util.Scanner;

import lombok.Setter;



//@Setter
public class SungJukInput implements SungJuk {
	private SungJukDTO2 sungJukDTO2;
	private List<SungJukDTO2> list;

	public void setSungJukDTO2(SungJukDTO2 sungJukDTO2) {
		this.sungJukDTO2 = sungJukDTO2;
	}

	public void setList(List<SungJukDTO2> list) {
		this.list = list;
	}

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
