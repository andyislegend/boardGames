package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class MessageDTO {
	private String body;
	private String friendUsername;
	
	public MessageDTO(String body, String friendUsername) {
		super();
		this.body = body;
		this.friendUsername = friendUsername;
	}

	public MessageDTO() {
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFriendUsername() {
		return friendUsername;
	}

	public void setFriendUsername(String friendUsername) {
		this.friendUsername = friendUsername;
	}

	@Override
	public String toString() {
		return "MessageDTO [body=" + body + ", friendUsername=" + friendUsername + "]";
	}
	
}
