package com.softserveinc.edu.boardgames.dto;

public class AllGamesForCurrentUserDTO {
	private String gameName;
	private String categoryName;

	public AllGamesForCurrentUserDTO() {
		// TODO Auto-generated constructor stub
	}

	public AllGamesForCurrentUserDTO(String gameName, String categoryName) {
		this.gameName = gameName;
		this.categoryName = categoryName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
