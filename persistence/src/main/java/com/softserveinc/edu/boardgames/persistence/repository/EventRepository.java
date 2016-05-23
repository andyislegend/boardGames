package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

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

//	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO"
//			+ "(e.id, e.name) from Events e Join e.users u where u.username =:username")
//	public List<EventsDTO> getUserEventsByUserName(@Param("username") String username);

	@Query(value = "INSERT INTO subscribed_events(event_id, user_id) VALUES (?1,?2)", nativeQuery = true)
	public void subscribeToEvent(Integer eventId, Integer userId);

	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO"
			+ "(e.id, e.name, e.description, e.location, e.date) from Event e")
	public List<EventsDTO> getAllEvents();
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO"
			+ "(e.id, e.name,e.description, e.location,e.date" + ") from Event e where e.id = :id")
	public EventsDTO getEventsById(@Param("id") Integer id);

	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO"
			+ "(e.id, e.name,e.description, e.location,e.date" + ") from Event e where e.name like %:name%")
	public List<EventsDTO> findAllEventsByWord(@Param("name") String name);

//	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO (event.id, "
//			+ "event.name, event.description, event.location, event.date) "
//			+ "from Event event where event.user like %:user%")
//	public List<EventsDTO> getAllUsersEvents(@Param("user") User user);
	
	
	// /**
	// * @author Vasyl Bervetskyy
	// **/
	// @Query(value = "SELECT " + "id, name, description, place, date " + "FROM
	// events WHERE userId = "
	// + "( SELECT id FROM users WHERE username = 'root')", nativeQuery = true)
	// public List<Object[]> getAllEventByUserName();

}
