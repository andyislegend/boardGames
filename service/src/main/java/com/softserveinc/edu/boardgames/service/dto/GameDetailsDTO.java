package com.softserveinc.edu.boardgames.service.dto;

public class GameDetailsDTO {
	
	String name;
	String description;
	String rules;
	
	public GameDetailsDTO() {}
	
	public GameDetailsDTO(String name, String description, String rules) {
		super();
		this.name = name;
		this.description = description;
		this.rules = rules;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
