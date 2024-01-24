package com.ss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.response.JwtRequest;
import com.ss.response.JwtResponse;
import com.ss.service.TokenService;

@RestController
public class JwtController {

	@Autowired
	public TokenService tokenService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> generateTokan(@RequestBody JwtRequest jwtRequest) throws Exception {
//		String username = null;
//
//		try {
//			username = jwtRequest.getUsername().replaceAll("\\s+", "").substring(0, 10);
//			System.out.println("HELLO USERNAME = "+username);
//		} catch (Exception e) {
//			return new ResponseEntity<String>(" please provied valid Phone Number !!  ", HttpStatus.BAD_REQUEST);
//		}
//
//		jwtRequest.setUsername(username);

		System.out.println(jwtRequest);
		JwtResponse token = null;
		try {
			token = tokenService.generateToken(jwtRequest);
		} catch (Exception e) {

			e.printStackTrace();
			return new ResponseEntity<String>(" Incorrect password or User !!  ", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<JwtResponse>(token, HttpStatus.OK);

	}
	
	
	@RequestMapping(value="/hii",method = RequestMethod.POST)
	public String show() {
		
		return"Hiii";
	}
}
