package com.softserveinc.edu.boardgames.persistence.repository;


import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.User;

/**
 *  @author Kobevka Anna
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

	Event findByGame(Game game);

	Event findByDate(Date date);

	Event findByName(String name);

	Event findByUser(User user);

}
