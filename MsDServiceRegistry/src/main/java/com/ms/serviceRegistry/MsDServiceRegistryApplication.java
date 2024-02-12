package com.ms.serviceRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsDServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsDServiceRegistryApplication.class, args);
	}

}
