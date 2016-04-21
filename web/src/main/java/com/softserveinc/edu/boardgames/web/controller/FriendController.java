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
}
