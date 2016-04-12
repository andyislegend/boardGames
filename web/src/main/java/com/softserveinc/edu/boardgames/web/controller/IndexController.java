package com.softserveinc.edu.boardgames.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public final String getIndexPage() {
        return "index";
    }
	
	@RequestMapping(value = "/home**", method = RequestMethod.GET)
	public final String getUserPage(){
		return "home";
	}
}
