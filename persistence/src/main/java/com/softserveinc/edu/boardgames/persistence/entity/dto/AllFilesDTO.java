package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.List;

public class AllFilesDTO {
	
	List<GameUserDTO> gameUsers;
	List<AllTournamentsDTO> tournaments;
	List<AllEventsDto> events;
	
	public AllFilesDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public List<GameUserDTO> getGameUsers() {
		return gameUsers;
	}
	public void setGameUsers(List<GameUserDTO> gameUsers) {
		this.gameUsers = gameUsers;
	}
	public List<AllTournamentsDTO> getTournaments() {
		return tournaments;
	}
	public void setTournaments(List<AllTournamentsDTO> tournaments) {
		this.tournaments = tournaments;
	}
	public List<AllEventsDto> getEvents() {
		return events;
	}
	public void setEvents(List<AllEventsDto> events) {
		this.events = events;
	}

}