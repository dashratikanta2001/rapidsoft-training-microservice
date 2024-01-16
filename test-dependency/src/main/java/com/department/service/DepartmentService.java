package com.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.dao.DepartmentDao;
import com.department.entity.Department;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	public Department saveDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentDao.save(department);
	}

	public Department findDepartmentById(Integer departmentId) {
		// TODO Auto-generated method stub
		return departmentDao.findByDepartmentId(departmentId);
	}
	
	
	
}
