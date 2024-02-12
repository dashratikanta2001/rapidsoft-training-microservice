package com.ms.userService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.userService.entity.User;

public interface UserDao extends JpaRepository<User, String>{

	
}
