package sample03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloSpring {

   public static void main(String[] args) {
      //스프링 설정파일을 읽어낸다.
      //ApplicationContext context=new FileSystemXmlApplicationContext("src/applicationContext.xml");
      ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
      MessageBean messageBean=(MessageBean) context.getBean("messageBean"); //부모에게 걸어줘야함
      messageBean.sayHello("Spring");
      System.out.println();
      
      MessageBean messageBean2= context.getBean("messageBean", MessageBean.class); //부모에게 걸어줘야함 캐스팅걸지 않고 이렇게 써줘도 된다 2가지 방법
      messageBean.sayHello("Spring");
      System.out.println();
      
      MessageBean messageBean3=(MessageBean) context.getBean("mb"); //부모에게 걸어줘야함 name 의 속성 으로 써줘도된다
      messageBean.sayHello("Spring");
      System.out.println();
      
   }

}