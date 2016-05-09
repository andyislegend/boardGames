package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Friend;
import com.softserveinc.edu.boardgames.persistence.entity.Status;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.repository.FriendRepository;
/**
 * This class is realization of DB CRUD other operation wich are used for manipulation with friend 
 * 
 * @author Vasyl Bervetskyy
 */
@Service
@Transactional
public class FriendService {
	
	/**
	 * This field are used for update status of friends in accordance
	 * notconsider - for make offer to another user
	 * accepted - for accept friendship
	 * 
	 * 
	 */
	private static Status NOTCONSIDER = new Status(1, "NOTCONSIDER");
	private static Status ACCEPTED = new Status(2, "ACCEPTED");
	
	@Autowired
	private FriendRepository friendRepository;
	
	public Integer findCountNoConsiderFrinds(String username) {
		return friendRepository.findCountNoConsiderFrinds(username);
	}
	
	public Friend addOfferToFriendship(User user, User userId) {
		Friend friend = new Friend();
		friend.setUser(user);
		friend.setUserId(userId);
		friend.setStatus(NOTCONSIDER);
		return friendRepository.saveAndFlush(friend);
	}
	
	public void acceptFrienship(User currentUser, User userId) {
		friendRepository.changeStatusOfFriendshipToAccepted(userId, currentUser);// change status of friendship to accepted
		Friend friend = new Friend();
		friend.setUser(currentUser);
		friend.setUserId(userId);
		friend.setStatus(ACCEPTED);
		friendRepository.saveAndFlush(friend); // make feedback in relationship between two users in table "Friends" 
	}
	
	public void rejectedFrienship(User currentUser, User userId) {
		friendRepository.changeStatusOfFriendshipToRejected(userId, currentUser);
	}
	
	public List<Friend> getAllMyOffering(String userName){
		return friendRepository.getAllMyOffering(userName);
	}
	
	public void cancelOffering(User currentUser, User otherUser){
		friendRepository.cancelOffering(currentUser, otherUser);
	}
	
}
