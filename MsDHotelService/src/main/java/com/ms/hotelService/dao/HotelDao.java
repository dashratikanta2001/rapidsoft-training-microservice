package com.ms.hotelService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.hotelService.entity.Hotel;

public interface HotelDao extends JpaRepository<Hotel, String>{

}
