package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.UserService;

@Controller
public class UsersController {
	
	@Autowired
	UserService userSevice;
	
	@RequestMapping(value = {"/users"}, method = RequestMethod.GET)
	@ResponseBody
    public ArrayList<User> getAllUsers(ArrayList<User> usersList) {
		usersList = (ArrayList<User>)userSevice.findAll();
		System.out.println(usersList);
		int a =0;
		int b = 0;
        return usersList;
    }
}
