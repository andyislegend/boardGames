package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class InfoFromApplierDTO {
	
	private String gameUserName;
	private String gameUserCategory;
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
	public InfoFromApplierDTO(String username, String message, String gameUserName, String gameUserCategory) {
		super();
		this.username = username;
		this.message = message;
		this.gameUserName = gameUserName;
		this.gameUserCategory = gameUserCategory;
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
	public String getGameUserName() {
		return gameUserName;
	}
	public void setGameUserName(String gameUserName) {
		this.gameUserName = gameUserName;
	}
	public String getGameUserCategory() {
		return gameUserCategory;
	}
	public void setGameUserCategory(String gameUserCategory) {
		this.gameUserCategory = gameUserCategory;
	}
}
