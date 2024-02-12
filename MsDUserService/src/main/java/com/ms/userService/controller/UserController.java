package com.ms.userService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.userService.entity.User;
import com.ms.userService.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//create
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user)
	{
		User saveUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}
	
	
	
	//single user get
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getSingleUser(@PathVariable("userId") String userId) {
		
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
		
	}
	
	
	//all user get
	
	@GetMapping
	public ResponseEntity<?> getAllUser() {
		
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
		
	}
	
	
	
	
}
