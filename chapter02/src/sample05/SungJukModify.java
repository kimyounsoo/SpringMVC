package sample05;

import java.util.List;
import java.util.Scanner;

import lombok.Setter;


public class SungJukModify implements SungJuk {
	//@Setter
	private List<SungJukDTO2> list;
	
	public void setList(List<SungJukDTO2> list) {
		this.list = list;
	}

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.print("이름을 입력하세요 : ");
		String name = scan.next();
		int sw=0;
		
		for(SungJukDTO2 sungJukDTO2 : list) {
			if(sungJukDTO2.getName().equals(name)) {
				sw=1;
				
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
				
//				System.out.print("국어 점수 입력 : ");
//				sungJukDTO2.setKor(scan.nextInt());
//				System.out.print("영어 점수 입력 : ");
//				sungJukDTO2.setEng(scan.nextInt());
//				System.out.print("수학 점수 입력 : ");
//				sungJukDTO2.setMath(scan.nextInt());
//				sungJukDTO2.setTot(sungJukDTO2.getKor()
//									+ sungJukDTO2.getEng()
//									+ sungJukDTO2.getMath());
//				sungJukDTO2.setAvg(sungJukDTO2.getTot()/3.0);
				
				System.out.println(name + "님의 데이터를 수정 하였습니다.");
			}//if
		
		}//for
		if(sw==0) {
			System.out.println("찾고자 하는 이름이 없습니다.");
		}
	}

}
