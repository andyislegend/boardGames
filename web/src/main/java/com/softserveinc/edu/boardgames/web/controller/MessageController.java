package com.softserveinc.edu.boardgames.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.Message;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.MessageDTO;
import com.softserveinc.edu.boardgames.service.MessageService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@RestController
public class MessageController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value = "/getAllMessage/{friendUserName}", method = RequestMethod.POST)
	public List<Message> getAllMessage(@PathVariable String friendUserName) {
		List<Message> listOfMessage =  messageService.getAllMessage(WebUtil.getPrincipalUsername(), friendUserName);
		return listOfMessage;
	}
	
	@RequestMapping(value = "/readMessage/{idMessage}", method = RequestMethod.POST)
	public void readMessage(@PathVariable Long idMessage) {
		messageService.changeStatusOfReadingOfMessage(idMessage);
	}

	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public void sendMessage(@RequestBody MessageDTO messageDTO) {
		System.out.println(messageDTO);	
		User currentUser = userService.getUser(WebUtil.getPrincipalUsername());
		User friendUser = userService.getUser(messageDTO.getFriendUsername());
		Message message = new Message();
		message.setCurrentUser(currentUser);
		message.setFriendUser(friendUser);
		message.setMessage(messageDTO.getBody());
		message.setDate(new Date());
		messageService.saveMessage(message);
	}
	
	@RequestMapping(value = "/findAllNotReadMessage", method = RequestMethod.GET)
	public Integer findAllNotReadMessage(){
		String currentUserName = WebUtil.getPrincipalUsername();
		return messageService.findAllNotReadMessage(currentUserName);
	}
}

