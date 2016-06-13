package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO;
import com.softserveinc.edu.boardgames.service.EventService;
import com.softserveinc.edu.boardgames.service.SubscribedUsersService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@Controller
public class EventsController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private SubscribedUsersService subUsersService;


	@RequestMapping(value = "/allEventsDTO",method = RequestMethod.GET)
    @ResponseBody
    public List<EventsDTO> getAllEvents() {
    	return eventService.getAllEvents();
    }
	
	@RequestMapping(value = "/allUsersEventsDTO",method = RequestMethod.GET)
    @ResponseBody
    public List<EventsDTO> getAllUsersEvents() {
    	return eventService.getAllNotExpiredEvents();
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
	
	@RequestMapping(value = "/subscribeToEvent", method = RequestMethod.POST)
	@ResponseBody
	public void subscribeToEvent(@RequestParam("id") Integer eventId) {
		subUsersService.subscribeToEvent(eventId, WebUtil.getPrincipalUsername());
	}
	
	@RequestMapping(value = "/getIfAlreadySub", method = RequestMethod.GET)
	@ResponseBody
	public Boolean getIfAlreadySub(@RequestParam("id") Integer eventId) {
		return subUsersService.isUserSubscribed(eventId, WebUtil.getPrincipalUsername());
	}

	@RequestMapping(value = "/getCountOfEventsByUser", method = RequestMethod.GET)
	@ResponseBody
	public Integer getCountOfNotifications(@RequestParam("id") Integer eventId) {
		return subUsersService.getCountOfEventsByUser(eventId, WebUtil.getPrincipalUsername());
	}
	
	@RequestMapping(value = "/unsubscribeFromEvent",method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> unsubscribeFromEvent(@RequestParam("id") Integer id){
		subUsersService.unsubscribeFromEvent(id, WebUtil.getPrincipalUsername());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
