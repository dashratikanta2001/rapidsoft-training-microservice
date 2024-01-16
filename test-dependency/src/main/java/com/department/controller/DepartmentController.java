package com.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.department.entity.Department;
import com.department.service.DepartmentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	
	@PostMapping("/")
	public ResponseEntity<?> saveDepartment(@RequestBody Department department) {
		//TODO: process POST request
		
		return ResponseEntity.ok(departmentService.saveDepartment(department));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findDepartmentById(@PathVariable("id") Integer departmentId) {
		return ResponseEntity.ok(departmentService.findDepartmentById(departmentId));
	}
	
	
}
