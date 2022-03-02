package com.example.springsecurity;

import com.example.springsecurity.model.User;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setEmail("a@g.com");
		user.setUsername("user");
		user.setPassword(this.bCryptPasswordEncoder.encode("password"));
		user.setRole("ROLE_ADMIN");

		User user2 = new User();
		user2.setEmail("b@g.com");
		user2.setUsername("user2");
		user2.setPassword(this.bCryptPasswordEncoder.encode("password"));
		user2.setRole("ROLE_ADMIN");
		this.userRepository.save(user);
		this.userRepository.save(user2	);

		userRepository.findAll().forEach(u -> System.out.println(u.getPassword() + " " + u.getUsername() + " " + u.getRole() + " " + u.getEmail()));
	}
}
