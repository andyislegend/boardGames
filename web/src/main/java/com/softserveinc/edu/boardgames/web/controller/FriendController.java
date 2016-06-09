package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.Friend;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.FriendService;
import com.softserveinc.edu.boardgames.service.NotificationService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

/**
 * 
 * This class is controller for manipulation with friends
 * 
 * @author Vasyl Bervetskyy
 */
@RestController
public class FriendController {
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private NotificationService notificationService;
	
	/**
	 * This method for get all current user's friends from DB
	 * 
	 */
	@RequestMapping(value = "/allFriends", method = RequestMethod.GET)
	public List<User> getAllFriends() {
		String userName = WebUtil.getPrincipalUsername();
		return userService.findAllFriends(userName);
	}
	
	/**
	 * This method for get number of offering to be your friend
	 * 
	 */
	@RequestMapping(value = "/allOffering", method = RequestMethod.GET)
	public int getAllOffering() {
		String userName = WebUtil.getPrincipalUsername();
		return friendService.findCountNoConsiderFrinds(userName);
	}
	
	/**
	 * 
	 * This method for get all current user's not consider friends from DB 
	 * 
	 */
	@RequestMapping(value = "/allOfferedUsers", method = RequestMethod.GET)
	public List<User> allOfferedUsers() {
		String userName = WebUtil.getPrincipalUsername();
		return userService.getAllNoConsiderFriendByUser(userName);
	}
	
	/**
	 * 
	 * This method for add users to the list of friends of current user
	 * 
	 */
	@RequestMapping(value = "/addUserToFriend",method = RequestMethod.POST)
	public User addUserToFriend(@RequestBody Integer id) {
		User currentUser = userService.findOne(WebUtil.getPrincipalUsername());
		User userId = userService.findById(id);
		friendService.acceptFrienship(currentUser, userId);
		return userId;
	}
	
	/**
	 * 
	 * This method for rejected relationships between current user and another user
	 * 
	 */
	@RequestMapping(value = "/rejectedUserToFriend",method = RequestMethod.POST)
	public User rejectedUserToFriend(@RequestBody Integer id) {
		User currentUser = userService.findOne(WebUtil.getPrincipalUsername());
		User userId = userService.findById(id);
		friendService.rejectedFrienship(currentUser, userId);
		return userId;
	}
	
	/**
	 * 
	 * This method for finding all user in application except you, your friends, and offered users
	 * 
	 */
	@RequestMapping(value = "/findAllUsers/{NameAndLastName}", method = RequestMethod.POST)
	public List<User> findAllUsers(@PathVariable String NameAndLastName) {
		return userService.findAllUserByFirstNameAndLastName(NameAndLastName, WebUtil.getPrincipalUsername());
	}
	
	/**
	 * 
	 * This method for offer an user to be your friend
	 * 
	 */
	@RequestMapping(value = "/addOfferToFriendship",method = RequestMethod.POST)
	public User addOfferToFriendship(@RequestBody Integer id) {
		User currentUser = userService.findOne(WebUtil.getPrincipalUsername());
		User userId = userService.findById(id);
		friendService.addOfferToFriendship(currentUser, userId);
		return userId;
	}
	
	/**
	 * This method for show you all user who you sent offer to friendship 
	 */
	@RequestMapping(value = "/allMyOffering", method = RequestMethod.GET)
	public List<Friend> allMyOffering() {
		String userName = WebUtil.getPrincipalUsername();
		return friendService.getAllMyOffering(userName);
	}
	
	/**
	 * This method for gives you possibility to cancel your offering to be friends 
	 */
	@RequestMapping(value = "/canselOffering/{otherUserName}",method = RequestMethod.POST)
	public void canselOffering(@PathVariable String otherUserName) {
		User currentUser = userService.findOne(WebUtil.getPrincipalUsername());
		User otherUser = userService.findOne(otherUserName);
		friendService.cancelOffering(currentUser, otherUser);
	}
	
	@RequestMapping(value = "/deleteFriend/{deleteUserName}",method = RequestMethod.POST)
	public void deleteFriend(@PathVariable String deleteUserName) {
		System.out.println(deleteUserName);
		User currentUser = userService.findOne(WebUtil.getPrincipalUsername());
		User otherUser = userService.findOne(deleteUserName);
		friendService.deleteFriend(currentUser, otherUser);
	}
	
	/**
	 * This method return amout of not yet read message per your friends 
	 */
	@RequestMapping(value = "/allMessageByCurrentUserFriends", method = RequestMethod.GET)
	public List<Integer> allFriend() {
		String currentUserName = WebUtil.getPrincipalUsername();
		List<User> listOfFriends = userService.findAllFriends(currentUserName);
		List<Integer> allMessagesByFriends = new ArrayList<Integer>();
		for(User friend: listOfFriends){
			allMessagesByFriends.add(notificationService.
					findAllNotReadMessageBySpecificFriend(friend.getUsername(), currentUserName));
		}
		return allMessagesByFriends;
	}
	
}
