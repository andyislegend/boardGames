package com.softserveinc.edu.boardgames.service.dto;

public class GameUserDTO {
	private String name;
	private String category;
	private int yearOfProduction;
	private String edition;
	private String description;
	private String rules;
	private int maxPlayers;
	private int minPlayers;
	
	public GameUserDTO() {
		// TODO Auto-generated constructor stub
	}

	public GameUserDTO(String name, String category, int yearOfProduction, String edition, String description,
			String rules, int maxPlayers, int minPlayers) {
		this.name = name;
		this.category = category;
		this.yearOfProduction = yearOfProduction;
		this.edition = edition;
		this.description = description;
		this.rules = rules;
		this.maxPlayers = maxPlayers;
		this.minPlayers = minPlayers;
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

	public int getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(int yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}
}
