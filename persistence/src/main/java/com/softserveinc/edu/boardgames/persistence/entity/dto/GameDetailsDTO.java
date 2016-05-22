package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class GameDetailsDTO {
	
	String name;
	Double userRating;
	Double generalRating;
	
	public GameDetailsDTO() {}
	
	public GameDetailsDTO(String name, Double userRating) {
		super();
		this.name = name;
		this.userRating = userRating;
	}
	public GameDetailsDTO(String name, Double userRating, Double generalRating) {
		super();
		this.name = name;
		this.userRating = userRating;
		this.generalRating = generalRating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Double getUserRating() {
		return userRating;
	}

	public void setUserRating(Double userRating) {
		this.userRating = userRating;
	}

	public Double getGeneralRating() {
		return generalRating;
	}

	public void setGeneralRating(Double generalRating) {
		this.generalRating = generalRating;
	}
}
