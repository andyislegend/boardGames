package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class GameDetailsDTO {
	
	String name;
	Integer userRating;
	Double generalRating;
	
	public GameDetailsDTO() {}
	
	public GameDetailsDTO(String name, Integer userRating) {
		super();
		this.name = name;
		this.userRating = userRating.intValue();
	}
	public GameDetailsDTO(String name, Integer userRating, Double generalRating) {
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

	public Integer getUserRating() {
		return userRating;
	}

	public void setUserRating(Integer userRating) {
		this.userRating = userRating;
	}

	public Double getGeneralRating() {
		return generalRating;
	}

	public void setGeneralRating(Double generalRating) {
		this.generalRating = generalRating;
	}
}
