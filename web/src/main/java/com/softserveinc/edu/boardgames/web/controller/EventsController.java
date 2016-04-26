package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.softserveinc.edu.boardgames.persistence.entity.Category;
import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllEventsDto;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.AllEventsMapper;
import com.softserveinc.edu.boardgames.persistence.repository.GameUserRepository;
import com.softserveinc.edu.boardgames.service.CategoryService;
import com.softserveinc.edu.boardgames.service.EventService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@RestController
public class EventsController {
	
	@Autowired
	EventService eventService;
	
	@RequestMapping(value = {"/eventspage"}, method = RequestMethod.GET)
		public List<AllEventsDto> getAllEvents() {
		List<Event> eventList = eventService.findAll();
		List<AllEventsDto> allEventsDto = new ArrayList<>();
		for(Event event : eventList){
			 allEventsDto.add(new AllEventsMapper().toDTO(event));
		}
		return  allEventsDto;
		
	}
	
	
	
	

}
