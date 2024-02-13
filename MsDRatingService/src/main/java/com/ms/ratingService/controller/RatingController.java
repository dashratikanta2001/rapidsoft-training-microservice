package com.ms.ratingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.ratingService.entity.Rating;
import com.ms.ratingService.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Rating rating) {
		// TODO: process POST request

		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
	}

	@GetMapping
	public ResponseEntity<?> getRatings() {
		// TODO: process POST request

		return ResponseEntity.ok(ratingService.getratings());
	}

	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/users/{userId}")
	public ResponseEntity<?> getRatingsByUserId(@PathVariable("userId") String userId) {
		// TODO: process POST request

		return ResponseEntity.ok(ratingService.getRatingByUserid(userId));
	}

	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<?> getRatingsByHotelId(@PathVariable("hotelId") String hotelId) {
		// TODO: process POST request

		return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
	}

}
