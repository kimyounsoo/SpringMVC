package sample04;

import java.io.FileWriter;
import java.io.IOException;

public class FileOutputter implements Outputter {
	private String filePath, fileName;
	
	public FileOutputter() {
		System.out.println("2. FileOutputter() 기본생성자");
		// TODO Auto-generated constructor stub
	}
	
	public void setFilePath(String filePath) {
		System.out.println("3. setFilePath(String filePath)");
		this.filePath = filePath;
	}

	public void setFileName(String fileName) {
		System.out.println("4. setFileName(String fileName)");
		this.fileName = fileName;
	}

	@Override
	public void output(String message) {
		try {
			FileWriter fileWriter = new FileWriter(filePath+fileName);
			fileWriter.write(message);
			fileWriter.close(); // 반드시 닫아줘야 파일이 정상적으로 저장된다
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
