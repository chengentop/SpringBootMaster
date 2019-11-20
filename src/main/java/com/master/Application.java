package com.master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@SpringBootApplication
@ComponentScan("com.master.config")
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class })
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

