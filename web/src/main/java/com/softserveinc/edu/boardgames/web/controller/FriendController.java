package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.Friend;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.FriendService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@RestController
public class FriendController {
	
	@Autowired
	FriendService friendService;
	
	@Autowired
	UserService userService; 
	
	@RequestMapping("/allFriends")
	public List<User> getAllFriends(){
		String userName = WebUtil.getPrincipalUsername();
		User user = userService.findOne(userName);
		List<Friend> listOfFriends = friendService.getAllFriends(user);
		List<User> list = new ArrayList<User>();
		for(int i = 0; i < listOfFriends.size(); i++){
			list.add(listOfFriends.get(i).getUserId());
		}
		return list;
	}
	
	@RequestMapping("/allOffering")
	public int getAllOffering(){
		String userName = WebUtil.getPrincipalUsername();
		User user = userService.findOne(userName);
		int countOfOffering = friendService.findCountNoConsiderFrinds(user);
		return countOfOffering;
	}
	
	@RequestMapping("/allOfferedUsers")
	public List<User> allOfferedUsers(){
		String userName = WebUtil.getPrincipalUsername();
		User user = userService.findOne(userName);
		List<Friend> listOfNoConsiderFriend = friendService.getAllNoConsiderFriendByUser(user);
		List<User> listOfUsers = new ArrayList<User>();
		for(int i = 0; i < listOfNoConsiderFriend.size(); i++){
			listOfUsers.add(listOfNoConsiderFriend.get(i).getUser());
		}
		return listOfUsers;
	}
	
	@RequestMapping(value = "/addUserToFriend",method = RequestMethod.POST)
	public User addUserToFriend(@RequestBody Integer id){
		User user = userService.findOne(WebUtil.getPrincipalUsername());
		User userId = userService.findById(id);
		friendService.acceptFrienship(user, userId);
		return userId;
	}
	
	@RequestMapping(value = "/rejectedUserToFriend",method = RequestMethod.POST)
	public User rejectedUserToFriend(@RequestBody Integer id){
		User user = userService.findOne(WebUtil.getPrincipalUsername());
		User userId = userService.findById(id);
		friendService.rejectedFrienship(user, userId);
		return userId;
	}
	
	@RequestMapping(value = "/findAllUsers/{name}", method = RequestMethod.POST)
	public List<User> findAllUsers(@PathVariable String name){
		System.out.println("**********************  name = " + name);
		String userName = name + "%";
		System.out.println("**********************  userName = " + userName);
		List<User> listOfUsers = userService.findAllUserByFirstName(userName);
		System.out.println("**********************  listOfUsers = " + listOfUsers);
		return listOfUsers;
	}
}
