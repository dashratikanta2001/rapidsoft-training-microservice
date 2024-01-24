package com.ss.dao;

import com.ss.entity.Department;

public interface DepartmentDao {
	
	Department save(Department department);

	Department getByCompanyIdAndDepartmentName(Integer id, String name);

	

}
