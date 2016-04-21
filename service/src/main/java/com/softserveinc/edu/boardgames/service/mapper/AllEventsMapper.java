package com.softserveinc.edu.boardgames.service.mapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.service.dto.AllEventsDto;

public class AllEventsMapper  implements GenericMapper<AllEventsDto, Event> {
	


	@Override
	public AllEventsDto toDTO(Event event) {
		
		AllEventsDto allEventsDto = new AllEventsDto();
		allEventsDto.setName(event.getName());
		allEventsDto.setDescription(event.getDescription());
		allEventsDto.setDatenum((event.getDate()).get(Calendar.DAY_OF_MONTH)+"");
		allEventsDto.setDatemonth(new SimpleDateFormat("MMMM", Locale.ENGLISH).format(event.getDate().getTime()));
		allEventsDto.setPlace(event.getPlace());
		allEventsDto.setGame(event.getGame().getName());
		allEventsDto.setUser(event.getUser().getFirstName());
		allEventsDto.setImgsrc(event.getImgsrc());
		
		return allEventsDto;
	}
}