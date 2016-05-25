package com.softserveinc.edu.boardgames.service;

import java.util.List;

import com.softserveinc.edu.boardgames.persistence.entity.SubscribedUsers;

public interface SubscribedUsersService {
	
	public List<SubscribedUsers> getAllNewUserSubscriber();
	
	public void changeStatusOfAllEventNotification(List<SubscribedUsers> listOfSubscribedUsers);
	
	public List<SubscribedUsers> getAllUsersSubToEvent(Integer id, String username);
	
	public void subscribeToEvent(Integer eventId, String username);
	
	public boolean isUserSubscribed(Integer eventId, String username);
	
}
