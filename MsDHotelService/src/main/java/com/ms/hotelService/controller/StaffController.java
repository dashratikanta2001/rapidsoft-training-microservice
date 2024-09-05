package com.ms.hotelService.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class StaffController {

	@GetMapping("/1")
	public ResponseEntity<?> getStaffs()
	{
		List<String> list = Arrays.asList("Ram", "Shyam", "Sita");
		return ResponseEntity.ok(list);
	}
}
