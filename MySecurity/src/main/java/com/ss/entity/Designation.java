package com.ss.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ss.dto.DesignationDto;
@Entity
@Table(name="designation")
public class Designation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;


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

	public static Designation fromDTO(DesignationDto designationDto) {
		Designation designation = new Designation();
		designation.setId(designationDto.getId());
		designation.setName(designationDto.getName().trim());
		return designation;
	}

}
