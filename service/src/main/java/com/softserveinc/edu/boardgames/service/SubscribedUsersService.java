package com.softserveinc.edu.boardgames.service;

import java.util.List;

import com.softserveinc.edu.boardgames.persistence.entity.SubscribedUsers;

public interface SubscribedUsersService {
	
	public List<SubscribedUsers> getAllNewUserSubscriber();
}
