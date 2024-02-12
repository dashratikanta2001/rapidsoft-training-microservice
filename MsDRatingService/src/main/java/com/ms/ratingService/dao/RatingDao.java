package com.ms.ratingService.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.ratingService.entity.Rating;

public interface RatingDao extends JpaRepository<Rating, String>{


	//custom finder method
	
	List<Rating> findByUserId(String userId);

	List<Rating> findByHotelId(String hotelID);
	
	
}
