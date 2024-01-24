package com.ss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.dto.FilterDto;
import com.ss.dto.SiteEmployeeDto;
import com.ss.response.Response;
import com.ss.service.SiteEmployeeService;

@RestController
@RequestMapping("api/site/")
public class SiteEmployeeController {

	
	@Autowired
	private SiteEmployeeService siteEmployeeService;
	
	
	@PostMapping("/add")
	public ResponseEntity<?>  addSite(@RequestBody SiteEmployeeDto siteEmployeeDto){
		
	Response<?>	response=siteEmployeeService.createSiteEmployee(siteEmployeeDto);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getSiteDetailsBYCompanyId( @RequestParam Integer companyId){
		
		Response<?> siteEmployeeDetails = siteEmployeeService.getAllSiteEmployeeDetails(companyId);
		
		return new ResponseEntity<>(siteEmployeeDetails,HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllDetails")
	public ResponseEntity<?> getSiteDetails(@RequestBody FilterDto filterDto){
		
		Response<?> siteEmployeeDetails = siteEmployeeService.getAll(filterDto);
		
		return new ResponseEntity<>(siteEmployeeDetails,HttpStatus.OK);
	}

}
