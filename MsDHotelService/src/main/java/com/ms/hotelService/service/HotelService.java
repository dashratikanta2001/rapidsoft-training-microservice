package com.ms.hotelService.service;

import java.util.List;

import com.ms.hotelService.entity.Hotel;

public interface HotelService {

	//create
	
	Hotel create(Hotel hotel);
	
	
	//get all
	List<Hotel> getAll();
	
	//get single
	Hotel get(String id);
	
}
