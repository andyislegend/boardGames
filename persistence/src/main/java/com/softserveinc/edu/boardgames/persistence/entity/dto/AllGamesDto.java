package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class AllGamesDto {
	
	private Integer id;
	private String name;
	private String categoryName;
	
	public AllGamesDto() {}
	public AllGamesDto(Integer id, String name, String categoryName) {
		super();
		this.id = id;
		this.name = name;
		this.categoryName = categoryName;
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
