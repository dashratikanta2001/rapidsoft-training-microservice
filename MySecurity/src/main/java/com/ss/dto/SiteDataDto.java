package com.ss.dto;

public class SiteDataDto {

	String name;

	Boolean isActive;

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

	public SiteDataDto() {

	}

	public SiteDataDto(String name, Boolean isActive) {
		super();
		this.name = name;
		this.isActive = isActive;
	}

}
