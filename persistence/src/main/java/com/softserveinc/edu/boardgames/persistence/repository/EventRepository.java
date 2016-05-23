package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllEventsDto;

/**
 * @author Andrii Petryk
 */

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

	public List<Event> findByUser(User user);

	List<Event> findAllEventsByName(String name);

	Event findById(Integer id);

	@Override
	void delete(Event event);

	 @Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.AllEventsDto"
	 + "(event.name, event.description, event.place, event.game.name, event.user.username, event.date, event.country, event.city) "
	 + "from Event event where event.name like %:name%")
	 public List<AllEventsDto> getAllEventsByEventName(@Param("name") String
	 name);

	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.AllEventsDto"
			+ "(event.name, event.description, event.place, event.game.name, event.user.username, event.date, event.country, event.city) "
			+ "from Event event")
	List<AllEventsDto> getAllEvents();

	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.AllEventsDto"
			+ "(event.name, event.description, event.place, event.game.name, event.user.username, event.date, event.country, event.city) "
			+ "from Event event where event.user like %:user%")
	List<AllEventsDto> getAllUsersEvents(@Param("user") User user);
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.AllEventsDto"
			+ "(event.name, event.description, event.place, event.game.name, event.user.username, event.date, event.country, event.city) "
			+ "from Event event where event.id like %:id%")
	public AllEventsDto getEventDTOById(@Param("id") Integer id);

//	/**
//	 * @author Vasyl Bervetskyy
//	 **/
//	@Query(value = "SELECT " + "id, name, description, place, date " + "FROM events WHERE userId = "
//			+ "( SELECT id FROM users WHERE username = 'root')", nativeQuery = true)
//	public List<Object[]> getAllEventByUserName();

}
