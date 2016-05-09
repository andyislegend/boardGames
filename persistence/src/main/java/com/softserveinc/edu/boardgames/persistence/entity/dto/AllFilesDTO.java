package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.List;

import com.softserveinc.edu.boardgames.persistence.entity.Event;

public class AllFilesDTO {
	
	List<GameUserDTO> gameUsers;
	List<AllTournamentsDTO> tournaments;
	List<Event> events;
	
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
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}

}