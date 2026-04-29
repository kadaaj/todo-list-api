package com.gabriel.todolistapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(
		exclude = {UserDetailsServiceAutoConfiguration.class}
)
public class TodoListApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApiApplication.class, args);
	}
}