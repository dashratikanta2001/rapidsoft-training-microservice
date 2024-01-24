package com.ss.service;

import javax.validation.Valid;

import com.ss.dto.DepartmentDto;
import com.ss.response.Response;

public interface DepartmentService {

	Response<?> saveDepartment(@Valid DepartmentDto departmentDto);


}
