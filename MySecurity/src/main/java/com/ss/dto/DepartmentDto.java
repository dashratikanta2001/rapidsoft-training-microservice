package com.ss.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ss.entity.Department;

public class DepartmentDto {
	private Integer id;

	@NotBlank(message = "Department name cannot be empty")
	@NotNull(message = "Department name cannot be null")
	private String name;

	@NotNull(message = "company is required")
	private CompanyDto company;

	private Boolean isActive;

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

	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public DepartmentDto(Integer id, String name, CompanyDto company, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.isActive = isActive;
	}

	public DepartmentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static DepartmentDto toDepartmentDto(Department department) {
		DepartmentDto departmentDto = new DepartmentDto();
		if (department == null) {
			return null;
		}
		departmentDto.setId(department.getId());
		departmentDto.setName(department.getName());
		departmentDto.setCompany(CompanyDto.toCompanyDto(department.getCompany()));
		departmentDto.setIsActive(department.getIsActive());
		return departmentDto;
	}

	public static Department toDepartmentEntity(DepartmentDto departmentDto) {
		Department department = new Department();
		if (departmentDto == null) {
			return null;
		}
		department.setId(departmentDto.getId());
		department.setName(departmentDto.getName());
		department.setCompany(CompanyDto.toCompany(departmentDto.getCompany()));
		department.setIsActive(departmentDto.getIsActive());
		return department;
	}
}
