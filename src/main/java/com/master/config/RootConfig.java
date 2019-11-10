package com.master.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan({"com.master.business.service"})
public class RootConfig {
	
}
