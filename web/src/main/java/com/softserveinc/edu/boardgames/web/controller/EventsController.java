package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllEventsDto;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.EventMapper;
import com.softserveinc.edu.boardgames.service.CityService;
import com.softserveinc.edu.boardgames.service.CountryService;
import com.softserveinc.edu.boardgames.service.EventService;
import com.softserveinc.edu.boardgames.service.GameService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

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

	@RequestMapping(value = "/allEvents", method = RequestMethod.GET)
	@ResponseBody
	public List<AllEventsDto> getAllEvents() {
		return eventService.getEventsDto();
	}

	@RequestMapping(value = "/allUserEvents")
	@ResponseBody
	public List<AllEventsDto> getAllUserEvents() {
		return eventService.findAllUsersEvents(WebUtil.getPrincipalUsername());
	}

	@RequestMapping(value = "/allEvents/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AllEventsDto getEventById(@PathVariable Integer id) {
		return eventService.getEventDTObyID(id);
	}

	@RequestMapping(value = "/allUserEvents/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AllEventsDto getUsersEventById(@PathVariable Integer id) {
		return eventService.getEventDTObyID(id);
	}

	@RequestMapping(value = "/addEvent", method = RequestMethod.POST)
	@ResponseBody
	public void addEvent(@RequestBody AllEventsDto allEventsDto) {

		Event event = EventMapper.toEntity(allEventsDto);
		event.setGame(gameService.findByName(allEventsDto.getGame()));
		event.setUser(userService.findOne(WebUtil.getPrincipalUsername()));
		
		eventService.create(event);

	}

}
