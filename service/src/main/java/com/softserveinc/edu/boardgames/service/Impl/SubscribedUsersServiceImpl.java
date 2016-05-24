package com.softserveinc.edu.boardgames.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.SubscribedUsers;
import com.softserveinc.edu.boardgames.persistence.repository.SubscribedUsersRepository;
import com.softserveinc.edu.boardgames.service.SubscribedUsersService;

@Service
@Transactional
public class SubscribedUsersServiceImpl implements SubscribedUsersService{

	@Autowired
	SubscribedUsersRepository subscribedUsersRepositorya;
	
	public List<SubscribedUsers> getAllNewUserSubscriber() {
		return subscribedUsersRepositorya.getAllNewUserSubscriber();
	}

}
