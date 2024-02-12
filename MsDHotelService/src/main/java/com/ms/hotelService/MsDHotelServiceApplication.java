package com.ms.hotelService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsDHotelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsDHotelServiceApplication.class, args);
	}

}
