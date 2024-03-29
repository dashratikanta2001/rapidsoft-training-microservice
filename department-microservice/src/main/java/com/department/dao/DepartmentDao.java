package com.department.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.department.entity.Department;

@Repository
public interface DepartmentDao extends JpaRepository<Department, Integer>{

	Department findByDepartmentId(Integer departmentId);

}
