package com.ss.dto;

import javax.validation.constraints.NotBlank;

import com.ss.entity.Company;
import com.ss.entity.Employee;
import com.ss.entity.SiteEmployee;

public class SiteEmployeeDto {

	private Integer id;
	@NotBlank(message = "Give Site Name")
	private String name;

	private Boolean isActive;

	private Integer companyId;

	
	private Integer employeeId;

	 public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public static SiteEmployeeDto convertToDto(SiteEmployee siteEmployee) {
	        if (siteEmployee == null) {
	            return null;
	        }

	        SiteEmployeeDto siteEmployeeDto = new SiteEmployeeDto();
	        siteEmployeeDto.setId(siteEmployee.getId());
	        siteEmployeeDto.setName(siteEmployee.getName());
	        siteEmployeeDto.setIsActive(siteEmployee.getIsActive());
	        siteEmployeeDto.setCompanyId(siteEmployee.getCompany().getId());
	        siteEmployeeDto.setEmployeeId(siteEmployee.getEmployee().getId());

	        return siteEmployeeDto;
	    }

	    public static SiteEmployee convertToEntity(SiteEmployeeDto siteEmployeeDto) {
	        if (siteEmployeeDto == null) {
	            return null;
	        }

	        SiteEmployee siteEmployee = new SiteEmployee();
	        siteEmployee.setId(siteEmployeeDto.getId());
	        siteEmployee.setName(siteEmployeeDto.getName());
	        siteEmployee.setIsActive(siteEmployeeDto.getIsActive());
	        
	        Company company = new Company(); 
	        company.setId(siteEmployeeDto.getCompanyId());
	        siteEmployee.setCompany(company);
	        
	        
	        Employee employee = new Employee();
	        
	        employee.setId(siteEmployeeDto.getEmployeeId());
	        
	        siteEmployee.setEmployee(employee);

	        return siteEmployee;
	    }

}
