package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.List;

public class AllFilesDTO {
	
	List<GameUserDTO> gameUsers;
	List<TournamentsDTO> tournaments;
	List<EventsDTO> events;
	
	public AllFilesDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public List<GameUserDTO> getGameUsers() {
		return gameUsers;
	}
	public void setGameUsers(List<GameUserDTO> gameUsers) {
		this.gameUsers = gameUsers;
	}
	public List<TournamentsDTO> getTournaments() {
		return tournaments;
	}
	public void setTournaments(List<TournamentsDTO> tournaments) {
		this.tournaments = tournaments;
	}
	public List<EventsDTO> getEvents() {
		return events;
	}
	public void setEvents(List<EventsDTO> events) {
		this.events = events;
	}

}