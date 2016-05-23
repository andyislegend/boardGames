package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.softserveinc.edu.boardgames.persistence.entity.Message;
import com.softserveinc.edu.boardgames.persistence.entity.Notification;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameNotificationDTO;
import com.softserveinc.edu.boardgames.persistence.enumeration.NotificationStatus;
import com.softserveinc.edu.boardgames.service.EventService;
import com.softserveinc.edu.boardgames.service.MessageService;
import com.softserveinc.edu.boardgames.service.NotificationService;
import com.softserveinc.edu.boardgames.service.TournamentService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@RestController
public class NotificationController {

	@Autowired
	MessageService messageService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TournamentService tournamentService;
	
//	@Autowired
//	EventService eventService;
	
	@Autowired
	NotificationService notificationService;
	
	
	@RequestMapping(value = "/getAllLastMessage", method = RequestMethod.GET)
	public List<Message> getAllMessage() {
		
		String currentUserName = WebUtil.getPrincipalUsername();
		List<User> listOfFriends = userService.findAllFriends(currentUserName);
		List<Message> listOfMessage = new ArrayList<Message>();
		for(User friend: listOfFriends){
			Message message = messageService.getLastMessage(currentUserName, friend.getUsername());
			if(message != null){
				listOfMessage.add(message);
			}
		}
		return listOfMessage;
	}
	
	@RequestMapping(value = "/getCurrentUserName", method = RequestMethod.GET)
	public String getCurrentUserName(){
		return WebUtil.getPrincipalUsername();
	}
	
	@RequestMapping(value = "/getAllCurrentUserTournament", method = RequestMethod.GET)
	public List<Object[]> getAllCurrentUserTournament(){
		String currentUserName = WebUtil.getPrincipalUsername();
		return tournamentService.getAllTournamentByUserName(currentUserName);
	}
	
//	@RequestMapping(value = "/getAllCurrentUserEvent", method = RequestMethod.GET)
//	public List<Object[]> getAllCurrentUserEvent(){
//		return eventService.getAllEventByUserName();
//	}
	
	@RequestMapping(value = "/getAllGameNotifications", method = RequestMethod.GET)
	@ResponseBody
	public List<GameNotificationDTO> getAllGameNotification() {
		return notificationService.getAllForUser(WebUtil.getPrincipalUsername());
	}
	
	@RequestMapping(value = "/markNotificationAsChecked/{notificationId}", method = RequestMethod.PUT)
	@ResponseBody
	public void markAsChecked(@PathVariable Integer notificationId) {
		Notification notify = notificationService.findById(notificationId);
		notify.setStatus(NotificationStatus.CHECKED.name());
		notificationService.update(notify);
	}
	
	@RequestMapping(value = "/getCountOfNotifications", method = RequestMethod.GET)
	@ResponseBody
	public Integer getCountOfNotifications() {
		return notificationService.getCountOfGameNotifications(WebUtil.getPrincipalUsername());
	}
}
