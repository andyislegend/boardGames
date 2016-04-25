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

import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllEventsDto;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.service.EventService;
import com.softserveinc.edu.boardgames.service.mapper.GameUserMapper;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@RestController
public class EventsController {
	
	@Autowired
	EventService eventService;
	
	
	
	@RequestMapping(value="/eventspage", method = RequestMethod.GET)
	@ResponseBody
	public List<AllEventsDto> getAllEvents(){
		return eventService.getEventsDto();
	}	
	
	
	/*@RequestMapping(value = "NewEvent", method = RequestMethod.POST)
	public String addNewGame(@RequestBody EventDto eventDto){	
		eventService.create(event);
		return "";
	}*/

}
