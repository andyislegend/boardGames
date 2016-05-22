package com.softserveinc.edu.boardgames.service;

import java.util.List;

import com.softserveinc.edu.boardgames.persistence.entity.Message;

public interface MessageService {
	
	public void saveMessage(Message message);
	
	public void changeStatusOfReadingOfMessage(Long messageId);
	
	public List<Message> getAllMessage(String currentUserName, String friendUserName);
	
	public Integer findAllNotReadMessage(String currentUserName);
	
	public Integer findAllNotReadMessageBySpecificFriend(String currentUserName, String friendUserName);
	
	public Message getLastMessage(String currentUserName, String friendUserName);
}
