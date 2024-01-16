package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		//TODO: process POST request
		
		return ResponseEntity.ok(userService.saveUser(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserWithDepartment(@PathVariable("id") Integer userId)
	{
		
		return ResponseEntity.ok(userService.getUserWithDepartment(userId));
	}
	
}
