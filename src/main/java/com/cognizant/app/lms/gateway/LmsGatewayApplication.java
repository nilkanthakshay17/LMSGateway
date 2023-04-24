package com.cognizant.app.lms.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@EnableDiscoveryClient
@CrossOrigin
public class LmsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsGatewayApplication.class, args);
		System.out.println("LMS Gateway Service Started");
	}

}
