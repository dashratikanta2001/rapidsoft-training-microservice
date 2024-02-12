package com.ms.userService.service;

import java.util.List;

import com.ms.userService.entity.User;

public interface UserService {

	
	//create
	User saveUser(User user);
	
	//get All User
	
	List<User> getAllUser();
	
	//Get single user of given Id
	User getUser (String userId);
	
	//TODO : delete
	//TODO : update
}
