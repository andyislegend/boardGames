package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author Volodymyr Krokhmaliuk
 *
 */
@Controller
public class CurrentUserGameController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@ResponseBody
	public String showGames(){
		return ""  ;
	}
}
