package com.softserveinc.edu.boardgames.service;

import java.util.Date;
import java.util.List;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO;

/**
 * @author Andrii Petryk
 */
public interface EventService {

	public List<Event> findAllEvents();

	public void createEvent(Event event);

	public void createEventFromDTO(EventsDTO dto);

	public void deleteEvent(int id);

	public void update(Event event);

	public void updateEventDTO(EventsDTO dto);

	public List<EventsDTO> getAllEvents();

	public Event geteventById(Integer id);

	public EventsDTO getEventDTOById(Integer id);

	public List<Event> findAll();

	public EventsDTO getEventsById(Integer id);

	public List<EventsDTO> getEventByWord(String name);

	public List<EventsDTO> getAllNotExpiredEvents();

	public List<Date> getAllDatesOfEvents();

	public Integer countEventsOnDate(Date date);

}
