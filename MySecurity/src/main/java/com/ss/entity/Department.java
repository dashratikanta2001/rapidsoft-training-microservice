package com.ss.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Hibernate;

@Entity
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	
	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "updated_on")
	private Date updatedOn;

	@Column(name = "is_active")
	private Boolean isActive;

	
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Employee> users = new ArrayList<>();


	
	

	public Department() {
		super();
	}




	public Department(Integer id, String name, Company company, List<Employee> users, Long createdBy, Long updatedBy,
			Date createdOn, Date updatedOn, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.users = users;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.isActive = isActive;
	}




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




	public Company getCompany() {
		return company;
	}




	public void setCompany(Company company) {
		this.company = company;
	}




	public List<Employee> getUsers() {
		return users;
	}




	public void setUsers(List<Employee> users) {
		this.users = users;
	}




	public Long getCreatedBy() {
		return createdBy;
	}




	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}




	public Long getUpdatedBy() {
		return updatedBy;
	}




	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
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




	public void initializeUsers() {
		Hibernate.initialize(users);
	}
}
