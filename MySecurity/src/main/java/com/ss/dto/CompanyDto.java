package com.ss.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ss.entity.Company;



public class CompanyDto {

	
	private Integer id;

	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Phone number is required")
	@Size(min = 10, max = 13, message = "Phone number should be 10 characters")
	private String phoneNumber;

	@NotBlank(message = "Address is required")
	private String address;


	@NotBlank(message = "About us is required")
	private String aboutUs;


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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAboutUs() {
		return aboutUs;
	}

	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public static CompanyDto toCompanyDto(Company company) {
		CompanyDto companyDTO = new CompanyDto();
		if (company == null) {
			return null;
		}
         
		companyDTO.setId(company.getId());
		companyDTO.setName(company.getName());
		companyDTO.setEmail(company.getEmail());
		companyDTO.setPhoneNumber(company.getPhoneNumber());
		companyDTO.setAddress(company.getAddress());
	
		
		companyDTO.setIsActive(company.isActive());
		
		return companyDTO;
	}

	public static Company toCompany(CompanyDto companyDTO) {
		if (companyDTO == null) {
			return null;
		}
		Company company = new Company();
		company.setId(companyDTO.getId());
		company.setName(companyDTO.getName());
		company.setEmail(companyDTO.getEmail());
		company.setPhoneNumber(companyDTO.getPhoneNumber());
		company.setAddress(companyDTO.getAddress());
		
		return company;
	}

}
