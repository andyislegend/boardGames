package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class ActionsDTO {

	private String date;
	private Integer tournaments;
	private Integer events;
	public ActionsDTO() {}
	public ActionsDTO(String date, Integer tournaments, Integer events) {
		super();
		this.date = date;
		this.tournaments = tournaments;
		this.events = events;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getTournaments() {
		return tournaments;
	}
	public void setTournaments(Integer tournaments) {
		this.tournaments = tournaments;
	}
	public Integer getEvents() {
		return events;
	}
	public void setEvents(Integer events) {
		this.events = events;
	}
}
