package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class GamesChartDTO {

	private String name;
	private String category;
	private Integer countOfGames;
	private Double generalRating;
	private Integer countOfRates;
	public GamesChartDTO() {}
	public GamesChartDTO(String name, String category, Double generalRating, Long countOfRates, Long countOfGames) {
		super();
		this.name = name;
		this.category = category;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
