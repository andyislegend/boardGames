package com.softserveinc.edu.boardgames.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Message;
import com.softserveinc.edu.boardgames.persistence.repository.MessageRepository;
import com.softserveinc.edu.boardgames.service.MessageService;

/**
 * This class is realization of DB CRUD other operation wich are used for manipulation with message 
 * 
 * @author Vasyl Bervetskyy
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageRepository messageRepository;
	
	public void saveMessage(Message message) {
		messageRepository.saveAndFlush(message);
	}
	
	public void changeStatusOfReadingOfMessage(Long messageId) {
		messageRepository.changeStatusOfReadingOfMessage(messageId);
	}
	
	public List<Message> getAllMessage(String currentUserName, String friendUserName) {
		return messageRepository.getAllMessage(currentUserName, friendUserName);
	}
	
	public Integer findAllNotReadMessage(String currentUserName) {
		return messageRepository.findAllNotReadMessage(currentUserName);
	}
	
	public Integer findAllNotReadMessageBySpecificFriend(String currentUserName, String friendUserName){
		return messageRepository.findAllNotReadMessageBySpecificFriend(currentUserName, friendUserName);
	}
	
	public Message getLastMessage(String currentUserName, String friendUserName){
		return messageRepository.getLastMessage(currentUserName, friendUserName);
	}

}
