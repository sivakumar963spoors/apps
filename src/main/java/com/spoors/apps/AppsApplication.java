package com.spoors.apps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spoors"})
public class AppsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppsApplication.class, args);
	}

}
