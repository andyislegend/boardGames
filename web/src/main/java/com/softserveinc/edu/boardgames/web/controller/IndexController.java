package com.softserveinc.edu.boardgames.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.edu.boardgames.web.util.WebUtil;

@Controller
public class IndexController {

	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public final String getIndexPage() {
        return "index";
    }
	
	@RequestMapping(value = "/home**", method = RequestMethod.GET)
	public final String getUserPage(Model model){
		model.addAttribute("user", WebUtil.getPrincipalUsername());
		
		return "home";
	}
	
}
