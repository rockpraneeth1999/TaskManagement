package com.example.TaskManagement;

import com.example.TaskManagement.Enum.Role;
import com.example.TaskManagement.model.User;
import com.example.TaskManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagementApplication {
	@Autowired
	static UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
		String username = "superuser";
		String password = "superuser";
		addSuperUser(username,password);
	}

	public static void addSuperUser(String username,String password){
		User superUser = new User();
		superUser.setName("Super User");
		superUser.setUsername(username);
		superUser.setPassword(password);
		superUser.setRole(Role.ADMIN);

		userRepository.save(superUser);
	}

}
