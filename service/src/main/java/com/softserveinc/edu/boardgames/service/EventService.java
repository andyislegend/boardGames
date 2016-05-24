package com.softserveinc.edu.boardgames.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.EventMapper;
import com.softserveinc.edu.boardgames.persistence.repository.EventRepository;

/**
 * @author Andrii Petryk
 */
@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	public List<Event> findAllEvents(){
		return eventRepository.findAll();
	}

	@Transactional
	public void createEvent(Event event) {
		eventRepository.save(event);
	}

	@Transactional
	public void deleteEvent(int id) {
		Event event = eventRepository.findById(id);
		eventRepository.delete(event);
	}

	@Transactional
	public void update(Event event) {
		eventRepository.saveAndFlush(event);
	}
	
	@Transactional
	public void updateEventDTO(EventsDTO dto) {
		Event event = geteventById(dto.getEventId());
		EventMapper.toEntity(dto, event);
		eventRepository.saveAndFlush(event);
	}

	
    public List<EventsDTO> getAllEvents() {
    	return  eventRepository.getAllEvents();
    }

    public Event geteventById(Integer id){
    	return eventRepository.findById(id);
    }
    
    public EventsDTO getEventDTOById(Integer id){
    	return eventRepository.getEventsById(id);
    }
    
    public List<Event> findAll(){
        return eventRepository.findAll();
    }
    
    public EventsDTO getEventsById(Integer id) {
    	return eventRepository.getEventsById(id);
    }
    
    public List<EventsDTO> getEventByWord(String name){
    	return eventRepository.findAllEventsByWord(name);
    }
        
    public List<EventsDTO> getAllNotExpiredEvents() {
    
    	List<EventsDTO> allEvents = eventRepository.getAllEvents();
    	List<EventsDTO> notExpired = new ArrayList<>();
    	Calendar calendar = Calendar.getInstance();
    	
    	for (EventsDTO event : allEvents) {
			
    		if (event.getDate().compareTo(calendar.getTime()) >= 0) {
				notExpired.add(event);
			}
		}	
    	
    	return notExpired;
    }

	// /**
	// * @author Vasyl Bervetskyy
	// **/
	// public List<Object[]> getAllEventByUserName() {
	// return eventRepository.getAllEventByUserName();
	// }

}
