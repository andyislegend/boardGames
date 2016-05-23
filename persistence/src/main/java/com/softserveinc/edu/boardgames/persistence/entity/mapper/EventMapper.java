package com.softserveinc.edu.boardgames.persistence.entity.mapper;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO;

/**
 * 
 * @author Andrii Petryk
 *
 */
public class EventMapper {
	
	public static Event toEntity(EventsDTO dto) {
		Event event = new Event();
		event.setName(dto.getName());
		event.setLocation(dto.getLocation());
		event.setDescription(dto.getDescription());
		event.setDate(dto.getDate());
		
		return event;
	}
}
