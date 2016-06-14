package com.softserveinc.edu.boardgames.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.Notification;
import com.softserveinc.edu.boardgames.persistence.entity.SubscribedUsers;
import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameNotificationDTO;
import com.softserveinc.edu.boardgames.persistence.repository.NotificationRepository;
import com.softserveinc.edu.boardgames.service.NotificationService;

/**
 * @author Taras Varvariuk, Vasul Berveckyi
 */
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

	final String NOTIFICATION_TYPE = "exchange";

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
		return notifyRepo.countOfUncheckedGameNotifications(username);
	}
	
	public void saveNotification(Notification notification){
		notifyRepo.saveAndFlush(notification);
	}
	
	public List<Notification> getAllMessage(String currentUserName, String friendUserName) {
		return notifyRepo.getAllMessage(currentUserName, friendUserName);
	}
	
	public void changeStatusOfReadingOfMessage(Integer messageId) {
		notifyRepo.changeStatusOfReadingOfMessage(messageId);
	}
	
	public Integer findAllNotReadMessage(String currentUserName) {
		return notifyRepo.findAllNotReadMessage(currentUserName);
	}
	
	public Integer findAllNotReadMessageBySpecificFriend(String currentUserName, String friendUserName) {
		return notifyRepo.findAllNotReadMessageBySpecificFriend(currentUserName, friendUserName);
	}
	
	public void addTournamentNotification(List<Tournament> listOfTournament) {
		for(Tournament tournament: listOfTournament) {
				for(User user: tournament.getUsers()) {
					Notification notification = new Notification();
					notification.setDate(tournament.getDateOfTournament());
					notification.setUser(user);
					notification.setMessage("Dear user, you have tournament tomorrow, pleas do not miss it. "
							+ "Name of tournament is \"" + tournament.getName() + "\" it will be "
									+ "in " + tournament.getCity() + ", " + tournament.getCountry() + " in ");
					notification.setType("NOTIFICATION");
					notifyRepo.save(notification);
				}
			}
	}
	
	public void addEventNotification(List<SubscribedUsers> listOfSubsriders) {
		for(SubscribedUsers su: listOfSubsriders) {
			Notification notification = new Notification();
			notification.setDate(su.getEvent().getDate());
			notification.setUser(su.getUser());
			notification.setMessage("Dear user, you have already sent request on \"" + su.getEvent().getName() + "\" event,"
					+ " we hope you will spend time with pleasure. Feel free, enjoy the game and be happy! Event will "
					+ "be in " + su.getEvent().getLocation() + " on ");
			notification.setType("EVENT");
			notifyRepo.save(notification);
		}
	}
	
	public List<Notification> getAllNotificationByUserName(String userName) {
		return notifyRepo.getAllNotificationByUserName(userName);
	}
	
	public List<Date> getAllNotificationDates() {
		List<Date> dates = new ArrayList<>();
		for (Notification n: this.findAllNotifications()) {
			Date date = n.getDate();
			if (date != null)
				dates.add(date);
		}
		return dates;
	}
	
	public Integer countNotificationsForSpecificDate(Date date) {
		return notifyRepo.countNotificationForSpecificDate(date);
	}

	public List<Notification> getAllNotification() {
		return notifyRepo.getAllNotification();
	}
	
	public void makeNotificationRead(List<Notification> listOfNotification) {
		for(Notification notification: listOfNotification){
			notifyRepo.makeNotificationRead(notification.getId());
		}		
	}

	public void processNotificationForExchange(String message, User forWhoom,
			User fromWhoom, GameUser gameUser) {
		Notification notification = new Notification();
		notification.setType(NOTIFICATION_TYPE);
		notification.setMessage(message);
		notification.setUserSender(fromWhoom);
		notification.setUser(forWhoom);
		notification.setGameUser(gameUser);
		this.update(notification);
	}
}
