package com.vhre.auth_boilerplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AuthBoilerplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthBoilerplateApplication.class, args);
	}

}
