package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class GameDetailsDTO {
	
	String name;
	Double rating;
	
	public GameDetailsDTO() {}
	
	public GameDetailsDTO(String name, Double rating) {
		super();
		this.name = name;
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
}
