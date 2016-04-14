package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.UserService;

@Controller
public class UsersController {
	
	UserService userSevice;
	
	@RequestMapping(value = {"/users"}, method = RequestMethod.GET)
    public List<User> getAllUsers() {
		List<User> userList = userSevice.findAll();
        return userList;
    }
}