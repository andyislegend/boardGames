package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.FriendService;
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
	FriendService friendService;
	
	@Autowired
	UserService userService; 
	
	/**
	 * This method for get all current user's friends from DB
	 * 
	 */
	@RequestMapping(value = "/allFriends", method = RequestMethod.GET)
	public List<User> getAllFriends() {
		String userName = WebUtil.getPrincipalUsername();
		List<User> list = userService.findAllFriends(userName);
		return list;
	}
	
	/**
	 * This method for get number of offering to be your friend
	 * 
	 */
	@RequestMapping(value = "/allOffering", method = RequestMethod.GET)
	public int getAllOffering() {
		String userName = WebUtil.getPrincipalUsername();
		int countOfOffering = friendService.findCountNoConsiderFrinds(userName);
		return countOfOffering;
	}
	
	/**
	 * 
	 * This method for get all current user's not consider friends from DB 
	 * 
	 */
	@RequestMapping(value = "/allOfferedUsers", method = RequestMethod.GET)
	public List<User> allOfferedUsers() {
		String userName = WebUtil.getPrincipalUsername();
		List<User> listOfUsers = userService.getAllNoConsiderFriendByUser(userName);
		return listOfUsers;
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
		if(userId == null) {
			return null;
		}
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
		if(userId == null) {
			return null;
		}
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
		List<User> listOfUsers = userService.findAllUserByFirstNameAndLastName(NameAndLastName, WebUtil.getPrincipalUsername());
		return listOfUsers;
	}
	
	/**
	 * 
	 * This method for offer an user to be your friend
	 * 
	 */
	@RequestMapping(value = "/addOfferToFriendship",method = RequestMethod.POST)
	public void addOfferToFriendship(@RequestBody Integer id) {
		User currentUser = userService.findOne(WebUtil.getPrincipalUsername());
		User userId = userService.findById(id);
		friendService.addOfferToFriendship(currentUser, userId);
	}
}
