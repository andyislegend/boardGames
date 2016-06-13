package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class GamesChartDTO {

	private String name;
	private Integer countOfGames;
	private Double generalRating;
	private Integer countOfRates;
	public GamesChartDTO() {}
	public GamesChartDTO(String name, Double generalRating, Long countOfRates, Long countOfGames) {
		super();
		this.name = name;
		this.generalRating = generalRating;
		this.countOfRates = countOfRates.intValue();
		this.countOfGames = countOfGames.intValue();
	}
	public GamesChartDTO(String name, Integer countOfGames) {
		super();
		this.name = name;
		this.countOfGames = countOfGames;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCountOfGames() {
		return countOfGames;
	}
	public void setCountOfGames(Integer countOfGames) {
		this.countOfGames = countOfGames;
	}
	public Double getGeneralRating() {
		return generalRating;
	}
	public void setGeneralRating(Double generalRating) {
		this.generalRating = generalRating;
	}
	public Integer getCountOfRates() {
		return countOfRates;
	}
	public void setCountOfRates(Integer countOfRates) {
		this.countOfRates = countOfRates;
	}
}
