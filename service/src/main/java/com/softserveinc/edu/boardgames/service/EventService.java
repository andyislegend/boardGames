package com.softserveinc.edu.boardgames.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO;
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
	
//	@Transactional
//    @Modifying
//    public void subscribeToEvent(Integer eventId, Integer userId){
//    	eventRepository.subscribeToEvent(eventId, userId);
//    }
	
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
    
    public List<Date> getAllDatesOfEvents() {
    	List<Date> dates = new ArrayList<>();
    	for (EventsDTO e: this.getAllEvents()) {
    		dates.add(e.getDate());
    	}
    	return dates;
    }
    
    public Integer countEventsOnDate(Date date) {
    	return eventRepository.countEventsForSpecificDate(date);
    }

}
