package com.softserveinc.edu.boardgames.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.SubscribedUsers;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.repository.EventRepository;
import com.softserveinc.edu.boardgames.persistence.repository.SubscribedUsersRepository;
import com.softserveinc.edu.boardgames.persistence.repository.UserRepository;
import com.softserveinc.edu.boardgames.service.SubscribedUsersService;

@Service
@Transactional
public class SubscribedUsersServiceImpl implements SubscribedUsersService{

	@Autowired
	SubscribedUsersRepository subscribedUsersRepository;
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public List<SubscribedUsers> getAllNewUserSubscriber() {
		return subscribedUsersRepository.getAllNewUserSubscriber();
	}

	@Override
	public void changeStatusOfAllEventNotification(List<SubscribedUsers> listOfSubscribedUsers) {
		
		for(SubscribedUsers su: listOfSubscribedUsers){
			subscribedUsersRepository.changeStatusOfState(su.getId());
		}		
	}

	@Override
	public List<SubscribedUsers> getAllUsersSubToEvent(Integer id, String username) {
		User u = userRepository.findByUsername(username);
		return subscribedUsersRepository.getAllUSersSubscribedToEvent(id, u.getId());
	}

	@Override
	@Transactional
	public void subscribeToEvent(Integer eventId, String username) {
		Event event = eventRepository.findById(eventId);
		User user = userRepository.findByUsername(username);
		SubscribedUsers subUser = new SubscribedUsers();
		subUser.setEvent(event);
		subUser.setUser(user);
		subscribedUsersRepository.save(subUser);
		
	}

	@Override
	public boolean isUserSubscribed(Integer eventId, String username) {
		User user = userRepository.findByUsername(username);
		if (subscribedUsersRepository.getAllUSersSubscribedToEvent(eventId, user.getId()).size() > 0) {
			return true;
		} else {
			return false;	
		}
		
	}

}
