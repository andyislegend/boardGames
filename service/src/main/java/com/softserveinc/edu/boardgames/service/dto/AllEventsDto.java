package com.softserveinc.edu.boardgames.service.dto;
import java.util.Date;
import java.util.GregorianCalendar;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.User;

public class AllEventsDto {
	
	private String name;
	private String description;
	private String datenum;
	private String datemonth;
	//private GregorianCalendar date;
	
	private String place;
	private String game;
	private String user;
	private String imgsrc;
	
	
	public AllEventsDto() {}
	public AllEventsDto(String name, String description, /*GregorianCalendar date, */String datedatenum,  String datemonth, String place,
				String game,	String user, String imgsrc) {
		super();
		this.name = name;
		this.description = description;
		this.datenum = datenum;
		this.datemonth = datemonth;
		//this.date = date;
		this.place = place;
		this.game=game; 
		this.user=user; 
		this.imgsrc = imgsrc;
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
	
	public String getDatenum() {
		return datenum;
	}
	public void setDatenum(String datenum) {
		this.datenum = datenum;
	}
	public String getDatemonth() {
		return datemonth;
	}
	public void setDatemonth(String datemonth) {
		this.datemonth = datemonth;
	}
	
	
	public String getImgsrc() {
		return imgsrc;
	}
	/*public GregorianCalendar getDate() {
		return date;
	}
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}*/
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
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
	
	
	
}
