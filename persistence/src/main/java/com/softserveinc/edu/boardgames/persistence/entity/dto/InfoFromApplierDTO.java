package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class InfoFromApplierDTO {
	
	private String username;
	private String message;
	
	public InfoFromApplierDTO() {
		super();
	}
	public InfoFromApplierDTO(String username, String message) {
		super();
		this.username = username;
		this.message = message;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
