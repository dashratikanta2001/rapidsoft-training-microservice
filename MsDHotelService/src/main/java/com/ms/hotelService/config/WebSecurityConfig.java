//package com.ms.hotelService.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig {
//
//	
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
//	{
//        http.authorizeHttpRequests(requests -> requests
//                .anyRequest()
//                .authenticated())
//                .oauth2ResourceServer(server -> server
//                        .jwt());
//		
//		return http.build();
//	}
//	
//}
