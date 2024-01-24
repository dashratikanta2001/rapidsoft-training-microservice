package com.ss.service;

import com.ss.dto.EmployeeDto;
import com.ss.entity.Employee;
import com.ss.response.Response;



public interface CredenstialService {

	Response<?> adduserInCrediantial(EmployeeDto employeeDto);

}
