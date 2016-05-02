package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

/**
 * Controller for receiving all users.
 * 
 * @author Volodymyr Terlyha
 *
 */
@Controller
public class UsersController {

	@Autowired
	UserService userSevice;

	/**
	 * Returns all users.
	 */
	@RequestMapping(value = {"/users"}, method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() {
		List<User> userList = userSevice.findAll();
		return userList;
	}
	
	@RequestMapping(value = {"/getProfile"}, method = RequestMethod.GET)
	@ResponseBody
	public User getUser() {
		User user = userSevice.findOne(WebUtil.getPrincipalUsername());
		return user;
	}
	
	@RequestMapping(value = {"/updateUserFirstLastName"}, method = RequestMethod.PUT)
	public User updateUserFirstLastName(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("username") String username) {
		userSevice.updateUserFirstLastName(firstName, lastName, username);
		User user = userSevice.findOne(WebUtil.getPrincipalUsername());
		return user;
	}
}