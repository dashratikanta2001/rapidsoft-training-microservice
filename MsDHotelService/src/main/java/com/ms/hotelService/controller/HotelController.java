package com.ms.hotelService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.hotelService.entity.Hotel;
import com.ms.hotelService.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	// create hotel
	@PostMapping
	public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {
		// TODO: process POST request

		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
	}

	// get single hotel
	@GetMapping("/{hotelId}")
	public ResponseEntity<?> getHotel(@PathVariable("hotelId") String hotelId) {
		// TODO: process POST request

		return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
	}

	// get all hotel
	@GetMapping
	public ResponseEntity<?> getAllHotel() {
		// TODO: process POST request

		return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAll());
	}

}
