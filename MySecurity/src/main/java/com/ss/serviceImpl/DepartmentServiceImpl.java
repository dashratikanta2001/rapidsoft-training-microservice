package com.ss.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ss.dto.DepartmentDto;
import com.ss.entity.Department;
import com.ss.repository.DepartmentRepository;
import com.ss.response.Response;
import com.ss.service.DepartmentService;


@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	public DepartmentRepository departmentRepository;

	@Override
	public Response<?> saveDepartment(DepartmentDto departmentDto) {


		if (departmentDto != null) {
			
			Department department = DepartmentDto.toDepartmentEntity(departmentDto);
		
			departmentRepository.save(department);

			return new Response<>("success", null, HttpStatus.OK.value());
		} else {
			return new Response<>("Department already exists", null, HttpStatus.BAD_REQUEST.value());
		}
	}

}