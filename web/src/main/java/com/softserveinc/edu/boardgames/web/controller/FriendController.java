package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.Friend;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.FriendService;
import com.softserveinc.edu.boardgames.service.UserService;

@RestController
public class FriendController {
	
	@Autowired
	FriendService friendService;
	
	@Autowired
	UserService userService; 
	
	@RequestMapping("/allFriends")
	public List<User> getAllFriends(){
		System.out.println("****in controller*****");
		User user = userService.findOne("root");
		List<Friend> listOfFriends = friendService.getAllFriends(user);
		
		Set<User> setOfUser = new HashSet<User>();
		
		for(int i = 0; i < listOfFriends.size(); i++){
			setOfUser.add(listOfFriends.get(i).getUserOne());
			setOfUser.add(listOfFriends.get(i).getUserTwo());
		}
		setOfUser.remove(user);
		System.out.println(setOfUser);
		System.out.println(user);
		List<User> list = new ArrayList<>(setOfUser);
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i).equals(user));
		}
		
		return list;
		
	}

}
