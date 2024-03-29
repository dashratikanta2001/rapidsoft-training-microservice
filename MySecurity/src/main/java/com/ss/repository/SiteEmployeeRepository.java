package com.ss.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ss.entity.SiteEmployee;



@Repository
@Transactional
public interface SiteEmployeeRepository  extends JpaRepository<SiteEmployee, Integer>{

	
	
	@Query(value ="select * from site_employee se where company_id=?1",  nativeQuery = true)
	List<SiteEmployee> getAllSiteEmployeeByCompanyId(Integer companyId);

}
