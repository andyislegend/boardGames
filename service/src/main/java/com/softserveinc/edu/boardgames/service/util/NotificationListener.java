package com.softserveinc.edu.boardgames.service.util;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.softserveinc.edu.boardgames.persistence.entity.Notification;
import com.softserveinc.edu.boardgames.persistence.entity.SubscribedUsers;
import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.service.MailService;
import com.softserveinc.edu.boardgames.service.NotificationService;
import com.softserveinc.edu.boardgames.service.SubscribedUsersService;
import com.softserveinc.edu.boardgames.service.TournamentService;

@Component
public class NotificationListener {
	
	@Autowired
	TournamentService tournamentService;
	
	@Autowired
	SubscribedUsersService subscribedUsersService;
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	MailService mailService;
	
	@Scheduled(fixedRate = 24*60*60*1000)
	public void setTournamentNotification(){
		List<Tournament> listOfTournament = tournamentService.getAllTommorowTournament();
		notificationService.addTournamentNotification(listOfTournament);
	}
	
	@Scheduled(fixedRate = 24*60*60*1000)
	public void setEventNotification(){
		List<SubscribedUsers> listOfSubsriders = subscribedUsersService.getAllNewUserSubscriber();
		notificationService.addEventNotification(listOfSubsriders);
		subscribedUsersService.changeStatusOfAllEventNotification(listOfSubsriders);
	}
	
	@Scheduled(fixedRate = 24*60*60*1000)
	public void sendNotificationMessage(){
		List<Notification> listOfNotification = notificationService.getAllNotification();
		for(Notification notification: listOfNotification){
			final String username = notification.getUser().getUsername();
			final String type = notification.getType();
			final String message = notification.getMessage();
			final String to = notification.getUser().getEmail();
			final Date date = notification.getDate();
			mailService.sendMailAboutNotification(to, message, type, username, date);
		}
	}
}
