package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class GamesChartDTO {

	private String name;
	private Integer countOfGames;
	public GamesChartDTO() {}
	public GamesChartDTO(String name, Integer y) {
		super();
		this.name = name;
		this.countOfGames = y;
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
	
}
