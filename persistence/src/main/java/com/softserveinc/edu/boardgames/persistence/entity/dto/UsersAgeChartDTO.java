package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class UsersAgeChartDTO {

	private String name;
	private Integer avgAge;
	
	public UsersAgeChartDTO(String name, Double avgAge) {
		super();
		this.name = name;
		this.avgAge = avgAge.intValue();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAvgAge() {
		return avgAge;
	}
	public void setAvgAge(Integer avgAge) {
		this.avgAge = avgAge;
	}
}
