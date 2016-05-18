package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.Message;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.MessageService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@RestController
public class NotificationController {

	@Autowired
	MessageService messageService;
	
	@Autowired
	UserService userService;
	
	
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
	
	
	
}
