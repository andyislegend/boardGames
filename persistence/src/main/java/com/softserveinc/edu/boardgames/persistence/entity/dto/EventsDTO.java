package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.Date;

public class EventsDTO {

	private Integer eventId;
	private String name;
	private String description;
	private String location;
	private Date date;

	public EventsDTO() {
	}

	public EventsDTO(Integer eventId, String name) {

		this.eventId = eventId;
		this.name = name;
	
	}
	
	public EventsDTO(String name, String description, String location, Date date) {

		this.name = name;
		this.description = description;
		this.location = location;
		this.date = date;

	}
	
	public EventsDTO(Integer eventId, String name, String description, String location, Date date) {

		this.eventId = eventId;
		this.name = name;
		this.description = description;
		this.location = location;
		this.date = date;

	}
	
	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
