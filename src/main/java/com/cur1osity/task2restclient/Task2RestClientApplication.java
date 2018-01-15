package com.cur1osity.task2restclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Task2RestClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(Task2RestClientApplication.class, args);
	}
}
