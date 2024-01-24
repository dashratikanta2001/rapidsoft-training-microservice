package com.ss.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ss.dto.FilterDto;
import com.ss.dto.PaginateRequestDto;
import com.ss.entity.Employee;

public interface EmployeeDao {

	
	
	List<Employee> getAll(Integer companyId,String name,String empCode);

	Employee getEmployeeByPnone(String phone);

	List<Employee> getAllEmployee(FilterDto filterDto);

	Page<Employee> getAllEmployees(PaginateRequestDto paginationRequest);

//	Employee createEmployee(Employee employee);

	//Employee findByEmail(String username);

	//List<Employee> getAll(Integer companyId);

//	List<Employee> getAlll(Integer companyId, String name, String empCode);
}
