package com.ss.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name = "master_crediantial")
public class CredentialMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JsonBackReference
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@Column(name = "employeename")
	private String username;

	@Column(name = "employee_password")
	private String password;

	@Column(name = "created_by")
	private String created_by;

	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "updated_by")
	private Date updatedOn;

	@Column(name = "is_active")
	private Boolean isActive;


	public CredentialMaster(Integer id, Employee employee, String username, String password, String created_by,
			Date createdOn, Date updatedOn, Boolean isActive) {
		super();
		this.id = id;
		this.employee = employee;
		this.username = username;
		this.password = password;
		this.created_by = created_by;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.isActive = isActive;
	
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getupdatedOn() {
		return updatedOn;
	}

	public void setupdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	
	public CredentialMaster() {
		super();
	}


	@Transient

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public String passwordEncoder(String password) {
		return passwordEncoder.encode(password);
	}

	public Boolean passwordMatcher(String password, String passwordInDB) {
		return passwordEncoder.matches(password, passwordInDB);
	}

}
