package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.List;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.Tournament;

public class AllFilesDTO {
	List<GameUser> gameUsers;
	List<Tournament> tournaments;
	List<Event> events;

	public AllFilesDTO() {

	}

	public AllFilesDTO(List<GameUser> gameUsers, List<Tournament> tournaments, List<Event> events) {
		this.gameUsers = gameUsers;
		this.tournaments = tournaments;
		this.events = events;
	}
}
