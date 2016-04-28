package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.UserService;

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
}
