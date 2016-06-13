package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.Date;

public class TournamentCreateDTO {
	private Integer gameId;
	private String name;
	private Integer countOfParticipants;
	private Date dateOfTournament;
	private Integer countryId;
	private Integer cityId;
	
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCountOfParticipants() {
		return countOfParticipants;
	}
	public void setCountOfParticipants(Integer countOfParticipants) {
		this.countOfParticipants = countOfParticipants;
	}
	public Date getDateOfTournament() {
		return dateOfTournament;
	}
	public void setDateOfTournament(Date dateOfTournament) {
		this.dateOfTournament = dateOfTournament;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}	
}