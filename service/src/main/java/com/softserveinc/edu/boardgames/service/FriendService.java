package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Friend;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.repository.FriendRepository;

@Service
@Transactional
public class FriendService {
	
	@Autowired
	private FriendRepository friendRepository;
	
//	public List<Friend> getAllFriends(User user){
//		return friendRepository.findAllFriendByUser(user);
//	}
//	
//	public Integer findCountNoConsiderFrinds(User user){
//		return friendRepository.findCountNoConsiderFrinds(user);
//	}
//	
//	public List<Friend> getAllNoConsiderFriendByUser(User user){
//		return friendRepository.getAllNoConsiderFriendByUser(user);
//	}
	
}
