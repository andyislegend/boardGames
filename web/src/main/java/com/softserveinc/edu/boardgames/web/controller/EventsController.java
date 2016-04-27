package com.softserveinc.edu.boardgames.web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllEventsDto;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.service.EventService;
import com.softserveinc.edu.boardgames.service.GameService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;
@RestController
public class EventsController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	GameService gameService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/eventspage", method = RequestMethod.GET)
	@ResponseBody
	public List<AllEventsDto> getAllEvents(){
		return eventService.getEventsDto();
	}	
	
	
	@RequestMapping(value = "/addEvent", method = RequestMethod.POST)
	@ResponseBody
	public void addEvent(@RequestBody AllEventsDto allEventsDto){	
		
		Event event = new Event();
		event.setName(allEventsDto.getName());
		event.setDescription(allEventsDto.getDescription());
		event.setPlace(allEventsDto.getPlace());
		event.setImgsrc(allEventsDto.getImgsrc());
		event.setGame(gameService.findByName(allEventsDto.getGame()));
		event.setUser(userService.findOne(WebUtil.getPrincipalUsername()));
		event.setDate(allEventsDto.getDate());
		eventService.create(event);
		
	}

}
