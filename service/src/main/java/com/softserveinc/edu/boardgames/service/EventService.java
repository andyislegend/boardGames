package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllEventsDto;
import com.softserveinc.edu.boardgames.persistence.repository.EventRepository;
import com.softserveinc.edu.boardgames.persistence.repository.UserRepository;

/**
 * @author Andrii Petryk
 */
@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private UserRepository userRepository;

	public List<Event> findByName(String name) {
		return eventRepository.findAllEventsByName(name);
	} 
	
	public List<Event> findByUser(String username){
		User user = userRepository.findByUsername(username);
		return eventRepository.findByUser(user);
	}
	
	public void deleteEvent(int id) {
		Event event = eventRepository.findById(id);
		eventRepository.delete(event);
	}
	
	public List<AllEventsDto> findAllUsersEvents(String username) {
		
		User user = userRepository.findByUsername(username);
		return eventRepository.getAllUsersEvents(user);
	}
	
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

	public List<AllEventsDto> getEventsDto() {
		return eventRepository.getAllEvents();
	}

	public List<Event> getAllEventsByName(String name) {
		return eventRepository.findAllEventsByName(name);
	}

	public List<AllEventsDto> getAllEventsByEventsName(String name) {
		return eventRepository.getAllEventsByEventName(name);
	}

//	/**
//	 * @author Vasyl Bervetskyy
//	 **/
//	public List<Object[]> getAllEventByUserName() {
//		return eventRepository.getAllEventByUserName();
//	}

}
