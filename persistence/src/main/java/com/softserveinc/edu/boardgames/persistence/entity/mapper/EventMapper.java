package com.softserveinc.edu.boardgames.persistence.entity.mapper;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllEventsDto;

/**
 * 
 * @author Andrii Petryk
 *
 */
public class EventMapper {
	
	public static Event toEntity(AllEventsDto dto) {
		Event event = new Event();
		event.setName(dto.getName());
		event.setPlace(dto.getPlace());
		event.setDescription(dto.getDescription());
		event.setDate(dto.getDate());;
		event.setCountry(dto.getCountry());
		event.setCity(dto.getCity());
		
		return event;
	}
}
