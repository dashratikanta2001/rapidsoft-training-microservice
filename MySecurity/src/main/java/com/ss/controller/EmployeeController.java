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

import com.ss.dto.EmployeeDto;
import com.ss.dto.EmployeePaginateRequestDto;
import com.ss.dto.PaginateRequestDto;
import com.ss.response.Response;
import com.ss.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	

//	@PostMapping("/create")
//	public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto, HttpServletRequest request) {
//		
//		System.out.println("bhcnciuhsc");
//
////		String header = request.getHeader("Authorization");
////
////		if (header == null) {
////
////			return new ResponseEntity<>(new Response<>("Unthorized Request", null, HttpStatus.UNAUTHORIZED.value()),
////					HttpStatus.UNAUTHORIZED);
////		}
////
////		else {
////
////			String token = header.substring(7);
////			Response<?> response = authenticate.getAuthenticateEmployee(token, employeeDto);
//
//			if (response.getStatus() == HttpStatus.OK.value()) {
//				Response<?> employee = employeeService.createEmployee(employeeDto);
//				return new ResponseEntity<>(employee, HttpStatus.valueOf(response.getStatus()));
//			} else {
//
//				return new ResponseEntity<>(null, (HttpStatus.BAD_REQUEST));
//			}
//
//		}
//
//	}
	
	@PostMapping("/create")
	public ResponseEntity<?> postMethodName(@RequestBody EmployeeDto employeeDto) {
		//TODO: process POST request
		
		Response<?> employee = employeeService.createEmployee(employeeDto);
		
		return ResponseEntity.ok(employee);
	}
	

	@PostMapping("/")
	public String get() {
		return "hhgd";
	}

//	@GetMapping("/getAll")
//	public ResponseEntity<?> getAllEmployeeByCompanyId(@RequestBody FilterDto filterDto){
//		
//		
//		Response<?> response = employeeService.getAllEmp(filterDto);
//		
//		return new ResponseEntity<>(response,HttpStatus.OK);
//	}
//	
//	

	@GetMapping("/getAllBySearch")
	public ResponseEntity<?> getAllEmployee(@RequestParam String search) {

		Response<?> response = employeeService.getAll(search);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllEmployeeByCompanyId(@RequestParam Integer companyId) {

		// Response<?> response = employeeService.getAllEmp(companyId);

		Response<?> response = employeeService.getAllEmp(companyId);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/paginated")
	public ResponseEntity<?> getPaginatedCompanies(@RequestBody PaginateRequestDto paginationRequest) {

		try {

			if (paginationRequest.getSize() <= 0) {
				return new ResponseEntity<>(
						new Response<>("Page Size Must be greaterthan one!!!", null, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.BAD_REQUEST);

			}

			EmployeePaginateRequestDto request = employeeService.getAllemployees(paginationRequest);

			return new ResponseEntity<>(new Response<>("Employees found", request, HttpStatus.OK.value()),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<>(
					new Response<>("Failed to fetch employees", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
