package com.ms.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsDApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsDApiGatewayApplication.class, args);
	}

}
