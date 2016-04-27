package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Friend;
import com.softserveinc.edu.boardgames.persistence.entity.Status;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.repository.FriendRepository;

@Service
@Transactional
public class FriendService {
	
	private static Status NOTCONSIDER = new Status(1, "NOTCONSIDER");
	private static Status ACCEPTED = new Status(2, "ACCEPTED");
	private static Status REJECTED = new Status(3, "REJECTED");
	
	@Autowired
	private FriendRepository friendRepository;
	
	public Integer findCountNoConsiderFrinds(String username){
		return friendRepository.findCountNoConsiderFrinds(username);
	}
	
	public Friend addOfferToFriendship(User user, User userId){
		Friend friend = new Friend();
		friend.setUser(user);
		friend.setUserId(userId);
		friend.setStatus(NOTCONSIDER);
		return friendRepository.saveAndFlush(friend);
	}
	
	public void acceptFrienship(User user, User userId){
		friendRepository.changeStatusOfFriendshipToAccepted(userId, user);
		Friend friend = new Friend();
		friend.setUser(user);
		friend.setUserId(userId);
		friend.setStatus(ACCEPTED);
		friendRepository.saveAndFlush(friend);
	}
	/*public void acceptFrienship(String curretUserName, String friendsUserName){
		friendRepository.changeStatusOfFriendshipToAccepted(friendsUserName, curretUserName);
		Friend friend = new Friend();
		
		User user = new User();
		user.setUsername(curretUserName);
		
		User user2 = new User();
		user.setUsername(friendsUserName);
		
		friend.setUser(user);
		friend.setUserId(user2);
		friend.setStatus(ACCEPTED);
		friendRepository.saveAndFlush(friend);
	}*/
	
	public void rejectedFrienship(User user, User userId){
		friendRepository.changeStatusOfFriendshipToRejected(userId, user);
	}
	/*public void rejectedFrienship(String curretUserName, String friendsUserName){
		friendRepository.changeStatusOfFriendshipToRejected(friendsUserName, curretUserName);
	}*/
	
}
