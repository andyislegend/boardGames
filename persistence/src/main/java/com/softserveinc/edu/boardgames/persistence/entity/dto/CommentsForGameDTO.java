package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.io.Serializable;

public class CommentsForGameDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer gameID;
	private String commentText;
	
	public CommentsForGameDTO() {
		// TODO Auto-generated constructor stub
	}

	public CommentsForGameDTO(Integer gameID, String commentText) {
		this.gameID = gameID;
		this.commentText = commentText;
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
}
