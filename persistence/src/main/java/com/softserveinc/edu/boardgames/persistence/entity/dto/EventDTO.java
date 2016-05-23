package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.Date;

public class EventDTO {

	private String name;
	private String description;
	private String place;
	private String game;
	private String user;
	private Date date;
	private String country;
	private String city;

	public EventDTO() {
	}

	public EventDTO(String name, String description, String place, String user, String game,
			Date date, String country, String city) {
		super();
		this.name = name;
		this.description = description;
		this.place = place;
		this.game = game;
		this.user = user;
		this.date = date;
		this.country = country;
		this.city = city;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
