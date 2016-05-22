package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.Date;

public class GameNotificationDTO {

	private String type;
	private String status;
	private String message;
	private String username;
	private Date date;
	
	public GameNotificationDTO(String type, String status, String message, String username, Date date) {
		super();
		this.type = type;
		this.status = status;
		this.message = message;
		this.username = username;
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
