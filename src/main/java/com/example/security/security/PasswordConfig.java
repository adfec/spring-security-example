package com.example.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

	// Bean annotation so spring can auto detect
	@Bean
	public PasswordEncoder passwordEncoder() {
		// Using the BCrypt algorythm to enconde, with a length of 10
		// Most popular encrypt currently
		return new BCryptPasswordEncoder(10);
	}
}
