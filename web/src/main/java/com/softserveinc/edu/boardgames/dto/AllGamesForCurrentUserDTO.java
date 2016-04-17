package com.softserveinc.edu.boardgames.dto;

public class AllGamesForCurrentUserDTO {
	private String name;
	private String category;

	public AllGamesForCurrentUserDTO() {
		// TODO Auto-generated constructor stub
	}

	public AllGamesForCurrentUserDTO(String gameName, String categoryName) {
		this.name = gameName;
		this.category = categoryName;
	}

	public String getGameName() {
		return name;
	}

	public void setGameName(String gameName) {
		this.name = gameName;
	}

	public String getCategoryName() {
		return category;
	}

	public void setCategoryName(String categoryName) {
		this.category = categoryName;
	}

}
