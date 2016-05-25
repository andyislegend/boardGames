package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.Date;

public class ActionsDTO {

	private String date;
	private Integer tournaments;
	private Integer events;
	private Integer exchanges;
	public ActionsDTO() {}
	public ActionsDTO(String date, Integer tournaments, Integer events, Integer exchanges) {
		super();
		this.date = date;
		this.tournaments = tournaments;
		this.events = events;
		this.exchanges = exchanges;
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
	public Integer getExchanges() {
		return exchanges;
	}
	public void setExchanges(Integer exchanges) {
		this.exchanges = exchanges;
	}
}