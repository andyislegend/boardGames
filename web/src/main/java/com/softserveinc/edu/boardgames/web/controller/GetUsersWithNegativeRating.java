package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.service.UserService;

@Controller
public class GetUsersWithNegativeRating {
	
	@Autowired
	UserService userSevice;
	
	@RequestMapping(value = {"/getUsersWithNegativeRating"}, method = RequestMethod.GET)
	@ResponseBody
	public List<String> getAllUsers() {
		List<String> userList = userSevice.findUserWithNeagativeRating();
		return userList;
	}
}
