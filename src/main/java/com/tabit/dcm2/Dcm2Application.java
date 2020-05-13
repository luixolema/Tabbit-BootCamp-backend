package com.tabit.dcm2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Dcm2Application {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new MessageDigestPasswordEncoder("SHA-256");
    }

	public static void main(String[] args) {
		SpringApplication.run(Dcm2Application.class, args);
	}
}
