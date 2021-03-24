package user.bean;

import org.springframework.context.annotation.ComponentScan;

import lombok.Data;

@Data
@ComponentScan("user.conf")
public class UserDTO {
	private String name;
	private String id;
	private String pwd;
}
