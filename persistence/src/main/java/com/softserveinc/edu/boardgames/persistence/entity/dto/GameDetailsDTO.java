package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class GameDetailsDTO {
	
	String name;
	String description;
	String rules;
	Double rating;
	
	public GameDetailsDTO() {}
	
	public GameDetailsDTO(String name, String description, String rules, Double rating) {
		super();
		this.name = name;
		this.description = description;
		this.rules = rules;
		this.rating = rating;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
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
