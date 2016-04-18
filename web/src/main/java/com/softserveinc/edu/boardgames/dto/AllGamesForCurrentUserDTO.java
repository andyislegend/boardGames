package com.softserveinc.edu.boardgames.dto;

public class AllGamesForCurrentUserDTO {
	private String name;
	private String category;

	public AllGamesForCurrentUserDTO() {
		// TODO Auto-generated constructor stub
	}

	public AllGamesForCurrentUserDTO(String name, String category) {
		this.name = name;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
}
