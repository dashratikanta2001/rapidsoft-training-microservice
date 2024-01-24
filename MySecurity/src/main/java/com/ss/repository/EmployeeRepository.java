package com.ss.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ss.entity.Employee;


@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	
	
	@Query(value ="select * from employee e where company_id=?1",  nativeQuery = true)
	List<Employee> getAllEmployeeByCompanyId(Integer companyId);

	@Query(value = "SELECT * FROM employee e WHERE e.firstname like  %:search% OR e.lastname like  %:search% OR e.emp_code like  %:search%", nativeQuery = true)
	List<Employee> search( @Param ("search")String search);

}
