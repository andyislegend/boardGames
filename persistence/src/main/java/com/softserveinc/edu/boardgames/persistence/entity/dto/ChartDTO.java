package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class ChartDTO {

	private String name;
	private Double y;
	public ChartDTO() {}
	public ChartDTO(String name, Double y) {
		super();
		this.name = name;
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
}
