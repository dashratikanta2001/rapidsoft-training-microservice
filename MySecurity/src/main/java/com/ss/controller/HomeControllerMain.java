package com.ss.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/home")
public class HomeControllerMain {

	
	@GetMapping("/hello")
	public ResponseEntity<?> base() {
		return ResponseEntity.ok("Hello User");
	}
	
	
	@GetMapping("/token")
	public ResponseEntity<?> generateToken() {
		return ResponseEntity.ok("Token Generated");
	}
	
}
