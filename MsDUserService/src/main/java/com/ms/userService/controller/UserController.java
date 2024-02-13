package com.ms.userService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.userService.entity.User;
import com.ms.userService.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//create
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user)
	{
		User saveUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}
	
	int retryCount=1;
	
	//single user get
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
//	@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallBack")
//	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<?> getSingleUser(@PathVariable("userId") String userId) {
		logger.info("Retry count: {}",retryCount);
		retryCount++;
		if(retryCount>3)
			retryCount=1;
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
		
	}
	
	
	//all user get
	
	@GetMapping
	public ResponseEntity<?> getAllUser() {
		
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
		
	}
	
	
	
	//Creating FallBack method for circuit breaker
	public ResponseEntity<?> ratingHotelFallBack(String userId, Exception ex)
	{
//		logger.info("Fallback is executed because service is down : ",ex.getMessage());
		
		User user = new User(userId, "Dummy", "dummy@gmail.com", "This user is created dummy because some service is down");
		
		return ResponseEntity.ok(user);
	}
	
	
}
