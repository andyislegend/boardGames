package com.softserveinc.edu.boardgames.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.Notification;
import com.softserveinc.edu.boardgames.persistence.entity.SubscribedUsers;
import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameNotificationDTO;

public interface NotificationService {

	Notification findById(Integer id);
	
	List<Notification> findAllNotifications();
	
	@Transactional
	void update(Notification notification);
	
	@Transactional
	void delete(Notification notification);
	
	List<GameNotificationDTO> getAllForUser(String username);
	
	Integer getCountOfGameNotifications(String username);
	
	void saveNotification(Notification notification);
	
	List<Notification> getAllMessage(String currentUserName, String friendUserName);
	
	void changeStatusOfReadingOfMessage(Integer messageId);
	
	Integer findAllNotReadMessage(String currentUserName);
	
	Integer findAllNotReadMessageBySpecificFriend(String currentUserName, String friendUserName);
	
	void addTournamentNotification(List<Tournament> listOfTournament);
	
	void addEventNotification(List<SubscribedUsers> listOfSubsriders);
	
	List<Notification> getAllNotificationByUserName(String userName);
	
	List<Date> getAllNotificationDates();
	
	Integer countNotificationsForSpecificDate(Date date);
	
	List<Notification> getAllNotification();
	
	void makeNotificationRead(List<Notification> listOfNotification);
	
	void processNotificationForExchange(String message, User forWhoom, 
			User fromWhoom, GameUser gameUser);
}
