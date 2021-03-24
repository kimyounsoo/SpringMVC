package sample03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import lombok.Getter;

//@Component("sungJukDTO")
@ComponentScan("spring.conf")
@Getter
public class SungJukDTO {
//	private @Value("홍길동")String name; //private String name="홍길동";
//	private @Value("97")int kor;
//	private @Value("100")int eng;
//	private @Value("95")int math;
	
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int tot;
	private double avg;
	
	@Autowired
	public void setName(@Value("홍길동") String name) {
		this.name = name;
	}
	
	@Autowired
	public void setKor(@Value("97") int kor) {
		this.kor = kor;
	}
	
	@Autowired
	public void setEng(@Value("100") int eng) {
		this.eng = eng;
	}
	
	@Autowired
	public void setMath(@Value("95") int math) {
		this.math = math;
	}
	
	public void setTot(int tot) {
		this.tot = tot;
	}
	
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	@Override
	public String toString() {
		return name+"\t"+kor+"\t"+eng+"\t"+math+"\t"+tot+"\t"+String.format("%.2f", avg);
	}
}











