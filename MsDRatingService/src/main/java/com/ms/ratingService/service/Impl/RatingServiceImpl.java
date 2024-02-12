package com.ms.ratingService.service.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.ratingService.dao.RatingDao;
import com.ms.ratingService.entity.Rating;
import com.ms.ratingService.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingDao ratingDao;
	
	@Override
	public Rating create(Rating rating) {
		// TODO Auto-generated method stub
		String randomUUID = UUID.randomUUID().toString();
		
		rating.setRatingId(randomUUID);
		
		return ratingDao.save(rating);
	}

	@Override
	public List<Rating> getratings() {
		// TODO Auto-generated method stub
		return ratingDao.findAll();
	}

	@Override
	public List<Rating> getRatingByUserid(String userId) {
		// TODO Auto-generated method stub
		return ratingDao.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		// TODO Auto-generated method stub
		return ratingDao.findByHotelId(hotelId);
	}

}
