package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.Notification;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameNotificationDTO;
import com.softserveinc.edu.boardgames.persistence.enumeration.NotificationStatus;
import com.softserveinc.edu.boardgames.service.NotificationService;
import com.softserveinc.edu.boardgames.service.SubscribedUsersService;
import com.softserveinc.edu.boardgames.service.TournamentService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@RestController
public class NotificationController {

	@Autowired
	UserService userService;
	
	@Autowired
	TournamentService tournamentService;
	
	@Autowired
	SubscribedUsersService subscribedUsersService;
	
	@Autowired
	NotificationService notificationService;
	
	@RequestMapping(value = "/getAllNotification", method = RequestMethod.GET)
	public List<Notification> getAllNotification(){
		List<Notification> listOFNotification = notificationService.getAllNotificationByUserName(WebUtil.getPrincipalUsername());
		return listOFNotification;
	}
	
	@RequestMapping(value = "/getCurrentUserName", method = RequestMethod.GET)
	public String getCurrentUserName(){
		return WebUtil.getPrincipalUsername();
	}
	
		
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
