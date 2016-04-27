package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class AllGamesDto {
	
	private Integer id;
	private String name;
	private String categoryName;
	private Integer minPlayers;
	private Integer maxPlayers;
	
	public AllGamesDto() {}
	public AllGamesDto(Integer id, String name, String categoryName, Integer minPlayers, Integer maxPlayers) {
		super();
		this.id = id;
		this.name = name;
		this.categoryName = categoryName;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
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
}
