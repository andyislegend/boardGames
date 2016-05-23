package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.Notification;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.MessageDTO;
import com.softserveinc.edu.boardgames.service.NotificationService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

/**
 * 
 * This class is controller for manipulation with message
 * 
 * @author Vasyl Bervetskyy
 */
@RestController
public class MessageController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	NotificationService notificationService;
	
	/**
	 * This method for get all current user's message from DB
	 */
	@RequestMapping(value = "/getAllMessage/{friendUserName}", method = RequestMethod.POST)
	public List<Notification> getAllMessage(@PathVariable String friendUserName) {
		
		List<Notification> listOfMessage = notificationService.getAllMessage(WebUtil.getPrincipalUsername(), friendUserName);
		return listOfMessage;
	}
	
	/**
	 * This method for change status of reding message from not read yet to already read 
	 */
	@RequestMapping(value = "/readMessage/{idMessage}", method = RequestMethod.POST)
	public void readMessage(@PathVariable Integer idMessage) {
		notificationService.changeStatusOfReadingOfMessage(idMessage);
	}
	
	/**
	 * This method for send message to your friend
	 */
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public void sendMessage(@RequestBody MessageDTO messageDTO) {
		User currentUser = userService.getUser(WebUtil.getPrincipalUsername());
		User friendUser = userService.getUser(messageDTO.getFriendUsername());
		
		Notification notification = new Notification();
		notification.setUserSender(currentUser);
		notification.setUser(friendUser);
		notification.setMessage(messageDTO.getBody());
		notification.setType("MESSAGE");
		notificationService.saveNotification(notification);
	}
	
	/**
	 * This method for send message to your friend
	 */
	@RequestMapping(value = "/findAllNotReadMessage", method = RequestMethod.GET)
	public Integer findAllNotReadMessage(){
		String currentUserName = WebUtil.getPrincipalUsername();
		return notificationService.findAllNotReadMessage(currentUserName);
	}
}

