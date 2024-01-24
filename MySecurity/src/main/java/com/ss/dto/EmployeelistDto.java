package com.ss.dto;

import com.ss.entity.Employee;

public class EmployeelistDto {

	Integer id;

	String name;

	String empCode;

	String deptName;

	String designName;

	String phoneNumber;

	String email;

	Integer companyId;

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

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDesignName() {
		return designName;
	}

	public void setDesignName(String designName) {
		this.designName = designName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

//	public static EmployeelistDto toDto(Employee employee) {
//		return new EmployeelistDto(employee.getFirstname(),employee.getEmpCode(),employee.getEmail(),employee.getCompany().getId()
//				,employee.getDepartment().getName(),employee.getDesignation().getName());
//	}

	public static EmployeelistDto toConvertDto(Employee employee) {

		EmployeelistDto employeelistDto = new EmployeelistDto();

		if (employee == null) {
			return null;
		}
		employeelistDto.setId(employee.getId());

		employeelistDto.setName(employee.getFirstname() + "" + employee.getLastname());

		employeelistDto.setEmail(employee.getEmail());
		employeelistDto.setEmpCode(employee.getEmpCode());
		employeelistDto.setPhoneNumber(employee.getPhone());
		employeelistDto.setDeptName(employee.getDepartment().getName());
		employeelistDto.setDesignName(employee.getDesignation().getName());
		employeelistDto.setCompanyId(employee.getCompany().getId());
		return employeelistDto;

	}

	public EmployeelistDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeelistDto(Integer id, String name, String empCode, String deptName, String designName,
			String phoneNumber, String email, Integer companyId) {
		super();
		this.id = id;
		this.name = name;
		this.empCode = empCode;
		this.deptName = deptName;
		this.designName = designName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.companyId = companyId;
	}
	
	

}
