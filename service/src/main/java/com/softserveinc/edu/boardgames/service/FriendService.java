package com.softserveinc.edu.boardgames.service;

import java.util.List;

import com.softserveinc.edu.boardgames.persistence.entity.Friend;
import com.softserveinc.edu.boardgames.persistence.entity.User;

/**
 * This interface is realization of DB CRUD other operation wich are used for manipulation with friends
 * 
 * @author Vasyl Bervetskyy
 */
public interface FriendService {
	
	public Integer findCountNoConsiderFrinds(String username);
	
	public Friend addOfferToFriendship(User user, User userId);
	
	public void acceptFrienship(User currentUser, User userId);
	
	public void rejectedFrienship(User currentUser, User userId);
	
	public List<Friend> getAllMyOffering(String userName);
	
	public void cancelOffering(User currentUser, User otherUser);
	
	public void deleteFriend(User currentUser, User otherUser);
	
}
