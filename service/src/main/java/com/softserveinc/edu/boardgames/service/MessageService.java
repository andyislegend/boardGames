package com.softserveinc.edu.boardgames.service;

import java.util.List;

import com.softserveinc.edu.boardgames.persistence.entity.Message;

/**
 * This interface is realization of DB CRUD other operation wich are used for manipulation with message
 * 
 * @author Vasyl Bervetskyy
 */
public interface MessageService {
	
	public void saveMessage(Message message);
	
	public void changeStatusOfReadingOfMessage(Long messageId);
	
	public List<Message> getAllMessage(String currentUserName, String friendUserName);
	
	public Integer findAllNotReadMessage(String currentUserName);
	
	public Integer findAllNotReadMessageBySpecificFriend(String currentUserName, String friendUserName);
	
	public Message getLastMessage(String currentUserName, String friendUserName);
}
