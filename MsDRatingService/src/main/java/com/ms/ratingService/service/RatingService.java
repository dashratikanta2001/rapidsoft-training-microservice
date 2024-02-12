package com.ms.ratingService.service;

import java.util.List;

import com.ms.ratingService.entity.Rating;

public interface RatingService {

	// create
	Rating create(Rating rating);

	// get all ratings

	List<Rating> getratings();

	// get all by user id
	List<Rating> getRatingByUserid(String userId);

	// get all by hotel
	List<Rating> getRatingByHotelId(String hotelId);

}
