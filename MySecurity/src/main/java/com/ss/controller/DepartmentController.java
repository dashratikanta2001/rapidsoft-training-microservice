package com.ss.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.dto.DepartmentDto;
import com.ss.response.Response;
import com.ss.service.DepartmentService;


@RestController
@RequestMapping("api/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@PostMapping("/create")
	public ResponseEntity<?> createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {

		if (departmentDto.getCompany() == null && departmentDto.getName() == null) {
			return new ResponseEntity<>(new Response<>("Provide Vallid Data", null, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.BAD_REQUEST);
		}

		Response<?> response = this.departmentService.saveDepartment(departmentDto);

		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
}
