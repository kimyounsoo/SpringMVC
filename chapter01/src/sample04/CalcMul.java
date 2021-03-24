package sample04;

public class CalcMul implements Calc {
	
	public CalcMul() {
		System.out.println("CalcMul 기본생성자");
	}

	@Override
	public void calculate(int x, int y) {
		System.out.println(x + " * " + y + " = " + (x*y));
		
	}
}
