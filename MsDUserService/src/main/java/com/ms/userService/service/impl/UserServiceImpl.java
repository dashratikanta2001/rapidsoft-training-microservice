package com.ms.userService.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.userService.dao.UserDao;
import com.ms.userService.entity.Hotel;
import com.ms.userService.entity.Rating;
import com.ms.userService.entity.User;
import com.ms.userService.exception.ResourceNotFoundException;
import com.ms.userService.externalService.HotelService;
import com.ms.userService.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub

		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userDao.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// implement all users rating.
		return userDao.findAll();
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		User user = userDao.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id is not found on server : " + userId));
		// Fetch rating of the above user from RATING SERVICE
		// http://localhost:8083/ratings/users/37ac13ea-130a-4700-af0f-26b11d0e0106

//		Rating[] ratingsForUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), Rating[].class);
		Rating[] ratingsForUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),
				Rating[].class);

		logger.info("{}", ratingsForUser);

		List<Rating> ratings = Arrays.stream(ratingsForUser).collect(Collectors.toList());

		ratings.stream().map(rating -> {
			// api call to hotel to get the hotel
			// http://localhost:8082/hotels/2fb7c237-a48f-4694-82cc-53fb2e17716f

			// restTemplate start
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);

//			Hotel hotel = forEntity.getBody();

//			logger.info("response stats code for hotel : {}",forEntity.getStatusCode());

			// restTemplate end

			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratings);

		return user;
	}

}
