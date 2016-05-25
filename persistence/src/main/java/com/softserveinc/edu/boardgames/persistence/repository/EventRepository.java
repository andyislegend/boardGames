package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO;

/**
 * @author Andrii Petryk
 */

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

	List<Event> findAllEventsByName(String name);

	Event findById(Integer id);

	@Override
	void delete(Event event);

//	@Query(value = "INSERT INTO subscribed_events(event_id, user_id) VALUES (?1,?2)", nativeQuery = true)
//	public void subscribeToEvent(Integer eventId, Integer userId);

	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO"
			+ "(e.id, e.name, e.description, e.location, e.date) from Event e")
	public List<EventsDTO> getAllEvents();
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO"
			+ "(e.id, e.name,e.description, e.location,e.date" + ") from Event e where e.id = :id")
	public EventsDTO getEventsById(@Param("id") Integer id);

	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO"
			+ "(e.id, e.name,e.description, e.location,e.date" + ") from Event e where e.name like %:name%")
	public List<EventsDTO> findAllEventsByWord(@Param("name") String name);	
	
//	@Query(value="select date from events", nativeQuery=true)
//	public List<Date> getAllDateOfEvents();
	
//	public List<Date> findAllDate();
	
	@Query("select COUNT(e) from Event e where e.date = :date")
	public Integer countEventsForSpecificDate(@Param("date")Date date);
	
}
