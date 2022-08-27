package com.doconnect.doconnectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DoconnectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoconnectServiceApplication.class, args);
	}

}
