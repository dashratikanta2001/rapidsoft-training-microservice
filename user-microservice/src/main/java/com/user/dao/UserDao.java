package com.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

	User findByUserId(Integer userId);

}
