package com.ss.service;

import com.ss.dto.EmployeeDto;
import com.ss.dto.EmployeePaginateRequestDto;
import com.ss.dto.PaginateRequestDto;
import com.ss.response.Response;

public interface EmployeeService {

	Response<?> createEmployee(EmployeeDto employeeDto);

	
	Response<?> getAll(String search);

	Response<?> getAllEmp(Integer companyId);


	EmployeePaginateRequestDto getAllemployees(PaginateRequestDto paginationRequest);


	//Response<?> getAllEmp(FilterDto filterDto);
	
	

}
