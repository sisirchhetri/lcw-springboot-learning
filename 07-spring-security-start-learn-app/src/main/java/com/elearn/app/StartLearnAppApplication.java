package com.elearn.app;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.elearn.app.entities.User;
import com.elearn.app.repositories.UserRepo;

@SpringBootApplication
public class StartLearnAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StartLearnAppApplication.class, args);
	}

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		
		User user = new User();
		user.setUserId("1");
		user.setName("ram");
		user.setEmail("abc@gmail.com");
		user.setPassword(passwordEncoder.encode("abc"));
		user.setActive(true);
		user.setAbout("User is Created");
		user.setActive(true);
		user.setCreatedAt(new Date());
		user.setEmailVerified(true);
		
		userRepo.findByEmail(user.getEmail())
				.ifPresentOrElse(user1 ->{
					System.out.println("Existing User :"+user1.getEmail());
				}, ()->{
					userRepo.save(user);
					System.out.println("User Created : "+user.getEmail());
				}
				);
		
		
		
	}

}
