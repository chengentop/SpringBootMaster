package com.master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


@SpringBootApplication
@ComponentScan("com.master.config")
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class })
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

