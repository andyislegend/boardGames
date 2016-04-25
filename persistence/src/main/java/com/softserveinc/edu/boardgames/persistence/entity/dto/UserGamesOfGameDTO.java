package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class UserGamesOfGameDTO {

	private Integer id;
	private String username;
	private String edition;
	private Integer yearOfProduction;
	
	public UserGamesOfGameDTO() {}
	
	public UserGamesOfGameDTO(Integer id, String username, String edition, Integer yearOfProduction) {
		super();
		this.id = id;
		this.username = username;
		this.edition = edition;
		this.yearOfProduction = yearOfProduction;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public Integer getYearOfProduction() {
		return yearOfProduction;
	}
	public void setYearOfProduction(Integer yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}
}
