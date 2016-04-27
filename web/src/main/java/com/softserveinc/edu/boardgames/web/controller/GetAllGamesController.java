package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.dto.AllGamesDto;
import com.softserveinc.edu.boardgames.service.GameService;

@RestController
public class GetAllGamesController {
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping(value="/getAllGames", method = RequestMethod.GET)
	@ResponseBody
	public List<AllGamesDto> getAllGames(){
		return gameService.getGamesDTO();
	}
}