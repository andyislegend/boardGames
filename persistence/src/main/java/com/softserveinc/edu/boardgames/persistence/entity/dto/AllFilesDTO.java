package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.List;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
public class AllFilesDTO {
	
	List<GameUser> gameUsers;
	List<AllTournamentsDTO> tournaments;
	List<Event> events;
	
	public AllFilesDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public List<GameUser> getGameUsers() {
		return gameUsers;
	}
	public void setGameUsers(List<GameUser> gameUsers) {
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