package com.ss.dto;

import java.util.List;

public class EmployeePaginateRequestDto {

	private int pageSize;

	private Long totalElements;

	private int totalPages;

	private List<EmployeeDto> employees;

	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<EmployeeDto> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}

	public EmployeePaginateRequestDto(int pageSize, Long totalElements, int totalPages, List<EmployeeDto> employees) {
		super();
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.employees = employees;
	}
	
	
	

}
