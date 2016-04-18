package com.softserveinc.edu.boardgames.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.repository.EventRepository;

/**
 *  @author Kobevka Anna
 */
@Service
@Transactional
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Transactional
    public void update(Event event) {
    	eventRepository.saveAndFlush(event);
    }

    public Event findByName(String name) {
        return  eventRepository.findByName (name);
    }
    
    public Event findByGame(Game game) {
        return eventRepository.findByGame (game);
    }
    
    public Event findByDate(Date date) {
        return eventRepository.findByDate (date);
    }
    
    public Event findByUser(User user) {
        return eventRepository.findByUser (user);
    }

}
