package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Notification;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameNotificationDTO;
import com.softserveinc.edu.boardgames.persistence.repository.NotificationRepository;

@Service
@Transactional
public class NotificationService {

	@Autowired
	private NotificationRepository notifyRepo;
	
	public Notification findById(Integer id) {
		return notifyRepo.findOne(id);
	}
	
	public List<Notification> findAllNotifications() {
		return notifyRepo.findAll();
	}
	
	@Transactional
	public void update(Notification notification) {
		notifyRepo.save(notification);
	}
	
	@Transactional
	public void delete(Notification notification) {
		notifyRepo.delete(notification);
	}
	
	public List<GameNotificationDTO> getAllForUser(String username) {
		return notifyRepo.getAllGamesNotifications(username);
	}
	
	public Integer getCountOfGameNotifications(String username) {
		return notifyRepo.countOfUncheckedNotifications(username);
	}
	
	public void saveNotification(Notification notification){
		notifyRepo.saveAndFlush(notification);
	}
	
	public List<Notification> getAllMessage(String currentUserName, String friendUserName){
		return notifyRepo.getAllMessage(currentUserName, friendUserName);
	}
	
	public void changeStatusOfReadingOfMessage(Integer messageId){
		notifyRepo.changeStatusOfReadingOfMessage(messageId);
	}
	
	public Integer findAllNotReadMessage(String currentUserName){
		return notifyRepo.findAllNotReadMessage(currentUserName);
	}
	
	public Integer findAllNotReadMessageBySpecificFriend(String currentUserName, String friendUserName){
		return notifyRepo.findAllNotReadMessageBySpecificFriend(currentUserName, friendUserName);
	}
	
	public Notification getLastMessage(String currentUserName, String friendUserName){
		return notifyRepo.getLastMessage(currentUserName, friendUserName);
	}
	
}
