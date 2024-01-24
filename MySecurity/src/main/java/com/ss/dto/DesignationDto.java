package com.ss.dto;

import com.ss.entity.Designation;

public class DesignationDto {

	
    private Integer id;
    
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
	public DesignationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DesignationDto(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	
	public static DesignationDto entitytoDTO(Designation designation) {
        DesignationDto designtionDto = new DesignationDto();
        if (designation == null) {
			return null;
		}
        designtionDto.setId(designation.getId());
        designtionDto.setName(designation.getName());
        return designtionDto;
    }
	
	public static Designation dtoToEntity(DesignationDto designationDto) {
	    Designation designation = new Designation();
	    if (designationDto == null) {
			return null;
		}
	    designation.setId(designationDto.getId());
	    designation.setName(designationDto.getName());

	    return designation;
	}
  
	
	
}
