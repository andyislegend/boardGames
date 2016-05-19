package com.softserveinc.edu.boardgames.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

/**
 * 
 * @author Andrii Petryk
 *
 */
@Controller
public class IndexController {

	@Autowired
	UserService userService;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public final String getIndexPage() {
		return "index";
	}

	@RequestMapping(value = "/home**", method = RequestMethod.GET)
	public final String getUserPage(Model model) {
		model.addAttribute("user", WebUtil.getPrincipalUsername());
		
		return "home";
	}

}
