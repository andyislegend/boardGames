package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.Date;

public class CommentsForGameDTO {
	
	private Integer gameID;
	private String commentText;
	private String username;
	private Date date;
	
	public CommentsForGameDTO() {
		// TODO Auto-generated constructor stub
	}

	public CommentsForGameDTO(Integer gameID, String commentText) {
		this.gameID = gameID;
		this.commentText = commentText;
	}
	
	public CommentsForGameDTO(Integer gameID, String commentText, String username, Date date) {
		this.gameID = gameID;
		this.commentText = commentText;
		this.username = username;
		this.date = date;
	}

	public Integer getGameID() {
		return gameID;
	}

	public void setGameID(Integer gameID) {
		this.gameID = gameID;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
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
