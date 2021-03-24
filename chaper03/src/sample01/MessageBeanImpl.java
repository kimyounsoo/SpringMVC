package sample01;

//핵심관심사항, Target
//아래 오버라이드 메소드들은 joinPoint
//그 중에서 showPrintBefore,viewPrintBefore은 pointCut
public class MessageBeanImpl implements MessageBean {

	private String str;

	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public void showPrintBefore() {
		
		System.out.println("showPrintBefore 메시지 = " + str);
	}

	@Override
	public void viewPrintBefore() {
	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("viewPrintBefore 메시지 = " + str);
	}


	@Override
	public void showPrintAfter() {
		System.out.println("showPrintAfter 메시지 = " + str);
		
	}

	@Override
	public void viewPrintAfter() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("viewPrintAfter 메시지 = " + str);
		
	}
	
	@Override
	public void showPrint() {
		System.out.println("showPrint 메시지 = " + str);
		
	}

	@Override
	public void viewPrint() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("viewPrint 메시지 = " + str);
		
	}	
	
	@Override
	public void display() {
		System.out.println("display 메시지 = "+str);
		
	}
}
