package com.blackiceinc.era;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class BlackiceincEraApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackiceincEraApplication.class, args);
	}
}
