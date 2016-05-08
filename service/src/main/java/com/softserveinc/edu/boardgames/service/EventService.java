package com.softserveinc.edu.boardgames.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllEventsDto;
import com.softserveinc.edu.boardgames.persistence.repository.EventRepository;

/**
 *  @author Kobevka Anna
 */
@Service
@Transactional
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	

	public List<Event> getAll() {
		return eventRepository.findAll();
	}

	@Transactional
	public void update(Event event) {
		eventRepository.saveAndFlush(event);
	}

	@Transactional
	public void create(Event event) {
		eventRepository.save(event);
	}
	
	public List<AllEventsDto> getEventsDto(){
		return eventRepository.getAllEvents();
	}
	
	public List<Event> getAllEventsByName(String name){
		return eventRepository.findAllEventsByName(name);
	}
	
	
}
