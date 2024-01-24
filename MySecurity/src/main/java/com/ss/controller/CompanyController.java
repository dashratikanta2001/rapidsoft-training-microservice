package com.ss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.dto.CompanyDto;
import com.ss.response.Response;
import com.ss.service.CompanyService;

@RestController
@RequestMapping("/api/company/")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@PostMapping("/add")
	public ResponseEntity<?> createCompany(@RequestBody CompanyDto companyDto) {

		Response<?> response = companyService.createCompany(companyDto);

		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getByID ( @PathVariable  Integer id){
		
		
		return new ResponseEntity<>(null,HttpStatus.OK);
	}

}
