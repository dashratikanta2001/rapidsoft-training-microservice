package com.ss.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.entity.Company;
import com.ss.entity.Department;
import com.ss.entity.Designation;
import com.ss.entity.Employee;

public class EmployeeDto {

	Integer id;

	@NotBlank(message = "Provied First Name")
	private String firstname;

	@NotBlank(message = "Provide Last Name")
	private String lastname;

	@NotBlank(message = "Email address is required")
	private String email;

	@NotBlank(message = "Phone Number Required")
	@Size(min = 10, max = 10, message = "Phone Must be 10 Digit")
	private String phone;

	@Size(min = 6, max = 6, message = "pincode Must be 6 Digit")
	private String pincode;

	private String created_by;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdOn;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatedOn;

	private Boolean isActive;

	private CompanyDto company;

	private DesignationDto designnation;

	private DepartmentDto department;

	@Pattern(regexp = "^[0-9a-zA-Z]{4,10}$", message = "Invalid Format for Employee code")
	private String empCode;

	private Boolean isPresent;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}

	public DesignationDto getDesignnation() {
		return designnation;
	}

	public void setDesignnation(DesignationDto designnation) {
		this.designnation = designnation;
	}

	public DepartmentDto getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDto department) {
		this.department = department;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public Boolean getIsPresent() {
		return isPresent;
	}

	public void setIsPresent(Boolean isPresent) {
		this.isPresent = isPresent;
	}

	public EmployeeDto() {

	}

	public static Employee toEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();

		if (employeeDto == null) {
			return null;
		}

		employee.setId(employeeDto.getId());
		employee.setFirstname(employeeDto.getFirstname());
		employee.setLastname(employeeDto.getLastname());
		employee.setEmail(employeeDto.getEmail());
		employee.setEmpCode(employeeDto.getEmpCode());

		Company company = new Company();

		company.setId(employeeDto.getCompany().getId());

		employee.setCompany(company);

		// Set Department
		Department department = new Department();
		department.setId(employeeDto.getDepartment().getId());
		employee.setDepartment(department);

		employee.setPhone(employeeDto.getPhone());
		employee.setIsActive(employeeDto.getIsActive());
		employee.setIsPresent(employeeDto.getIsPresent());

		// Set Designation
		Designation designation = new Designation();
		designation.setId(employeeDto.getDesignnation().getId());
		employee.setDesignation(designation);

		employee.setUpdatedOn(employeeDto.getUpdatedOn());

		return employee;
	}

	public static EmployeeDto toDto(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();

		if (employee == null) {
			return null;
		}

		employeeDto.setId(employee.getId());
		employeeDto.setFirstname(employee.getFirstname());
		employeeDto.setLastname(employee.getLastname());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setEmpCode(employee.getEmpCode());
		employeeDto.setCompany(CompanyDto.toCompanyDto(employee.getCompany()));
		employeeDto.setCreatedOn(employee.getCreatedOn());
		employeeDto.setCreated_by(employee.getCreated_by());

		// Set Department
		employeeDto.setDepartment(DepartmentDto.toDepartmentDto(employee.getDepartment()));

		employeeDto.setPhone(employee.getPhone());
		employeeDto.setIsActive(employee.getIsActive());
		employeeDto.setIsPresent(employee.getIsPresent());
		// Set Designation
		employeeDto.setDesignnation(DesignationDto.entitytoDTO(employee.getDesignation()));
		employeeDto.setUpdatedOn(employee.getUpdatedOn());

		return employeeDto;
	}

}

//	public static Employee toEmployee(EmployeeDto employeeDto) {
//		Employee employee = new Employee();
//
//		if (employeeDto == null) {
//			return null;
//		}
//
//		employee.setId(employeeDto.getId());
//		employee.setFirstname(employeeDto.getFirstname());
//		employee.setLastname(employeeDto.getLastname());
//		employee.setEmail(employeeDto.getEmail());
//		employee.setEmpCode(employeeDto.getEmpCode());
//		employee.setCompany(employeeDto.getCompany());
//
//		Department department = new Department();
//		department.setId(employeeDto.getDepartment().getId());
//		employee.setDepartment(department);
//		
//		employee.setPhone(employeeDto.getPhone());
//		employee.setIsActive(employeeDto.getIsActive());
//		employee.setIsPresent(employeeDto.getIsPresent());
//
//		Designation designation = new Designation();
//		designation.setId(employee.getDesignation().getId());
//		employeeDto.setDesignnation(designation);
//
//		employee.setUpdatedOn(employeeDto.getUpdatedOn());
//
//		return employee;
//	}
//
//	public static EmployeeDto toDto(Employee employee) {
//		EmployeeDto employeeDto = new EmployeeDto();
//
//		if (employee == null) {
//			return null;
//		}
//
//		employeeDto.setId(employee.getId());
//		employeeDto.setFirstname(employee.getFirstname());
//		employeeDto.setLastname(employee.getLastname());
//		employeeDto.setEmail(employee.getEmail());
//		employeeDto.setEmpCode(employee.getEmpCode());
//		employeeDto.setCompany(employee.getCompany());
//		employeeDto.setCreatedOn(employee.getCreatedOn());
//		employeeDto.setCreated_by(employee.getCreated_by());
//
//		employeeDto.setDepartment(employee.getDepartment());
//
//		employeeDto.setPhone(employee.getPhone());
//		employeeDto.setIsActive(employee.getIsActive());
//		employeeDto.setIsPresent(employee.getIsPresent());
//		employeeDto.setDesignnation(employee.getDesignation());
//		employeeDto.setUpdatedOn(employee.getUpdatedOn());
//
//		return employeeDto;
//	}

//}
