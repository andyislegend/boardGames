package com.softserveinc.edu.boardgames.persistence.repository;


import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllEventsDto;

/**
 *  @author Kobevka Anna
 */


@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.AllEventsDto"
			+"(event.name, event.description, event.place, event.imgsrc, event.user.firstName, event.game.name, event.date) "
			+ "from Event event where event.name like %:name%")
	public List<AllEventsDto> getAllEventsByWord(@Param("name") String name);

	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.AllEventsDto"
			+"(event.name, event.description, event.place, event.imgsrc, event.user.firstName, event.game.name, event.date) "
			+ "from Event event")
      List<AllEventsDto> getAllEvents();

	List<Event> findAllEventsByName(String name);

}
