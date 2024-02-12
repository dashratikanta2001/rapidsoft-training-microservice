package com.ms.hotelService.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.hotelService.dao.HotelDao;
import com.ms.hotelService.entity.Hotel;
import com.ms.hotelService.exception.ResourceNotFoundException;
import com.ms.hotelService.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelDao hotelDao;
	
	@Override
	public Hotel create(Hotel hotel) {
		// TODO Auto-generated method stub
		
		String randomUUID = UUID.randomUUID().toString();
		hotel.setId(randomUUID);
		return hotelDao.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		// TODO Auto-generated method stub
		return hotelDao.findAll();
	}

	@Override
	public Hotel get(String id) {
		// TODO Auto-generated method stub
		return hotelDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id not found. :"+ id));
	}

}
