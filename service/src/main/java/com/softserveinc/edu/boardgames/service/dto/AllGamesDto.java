package com.softserveinc.edu.boardgames.service.dto;

public class AllGamesDto {
	
	private String name;
	private String categoryName;
	private String description;
	private Integer minPlayers;
	private Integer maxPlayers;
	private String rating;
	
	public AllGamesDto() {}
	public AllGamesDto(String name, String categoryName, String description, Integer minPlayers, Integer maxPlayers,
			String rating) {
		super();
		this.name = name;
		this.categoryName = categoryName;
		this.description = description;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.rating = rating;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getMinPlayers() {
		return minPlayers;
	}
	public void setMinPlayers(Integer minPlayers) {
		this.minPlayers = minPlayers;
	}
	public Integer getMaxPlayers() {
		return maxPlayers;
	}
	public void setMaxPlayers(Integer maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
}