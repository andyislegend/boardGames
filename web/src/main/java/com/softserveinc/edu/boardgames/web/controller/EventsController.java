package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO;
import com.softserveinc.edu.boardgames.service.CityService;
import com.softserveinc.edu.boardgames.service.CountryService;
import com.softserveinc.edu.boardgames.service.EventService;
import com.softserveinc.edu.boardgames.service.GameService;
import com.softserveinc.edu.boardgames.service.UserService;

@Controller
public class EventsController {

	@Autowired
	EventService eventService;

	@Autowired
	UserService userService;

	@Autowired
	CityService cityService;

	@Autowired
	CountryService countryService;

	@Autowired
	GameService gameService;


	@RequestMapping(value = "/allEventsDTO",method = RequestMethod.GET)
    @ResponseBody
    public List<EventsDTO> getAllEvents() {
    	return eventService.getAllEvents();
    }
	
	@RequestMapping(value = "/allEvents")
    @ResponseBody
    public List<Event> findAllEvents() {
    	return eventService.findAllEvents();
    }
	
	@RequestMapping(value = {"/getEventDTO"}, method = RequestMethod.GET)
	@ResponseBody
	public EventsDTO getEventDTO(@RequestParam("id") Integer id) {
		return eventService.getEventDTOById(id);
	}

	@RequestMapping(value = "/cancelEvent",method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteEvent(@RequestParam("id") Integer id){
		eventService.deleteEvent(id);
	}
	
	@RequestMapping(value = {"/updateEvent"}, method = RequestMethod.PUT)
	@ResponseBody
	public void updateEvent(@RequestBody EventsDTO event) {
		eventService.updateEventDTO(event);
	}
	
	@RequestMapping(value = {"/addEvent"}, method = RequestMethod.POST)
	@ResponseBody
	public void addEvent(@RequestBody EventsDTO event) {
		eventService.createEventFromDTO(event);
	}
}
