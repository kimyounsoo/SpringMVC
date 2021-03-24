package sample01;

public interface MessageBean {
	public void showPrintBefore();
	public void viewPrintBefore();
	
	public void showPrintAfter();
	public void viewPrintAfter();
	
	//showPrint return값이 생기게 바꿔준다
	public String showPrint();
	public void viewPrint();
	
	public void display();
}
