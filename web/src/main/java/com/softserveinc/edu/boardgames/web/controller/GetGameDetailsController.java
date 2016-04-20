package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.service.GameService;
import com.softserveinc.edu.boardgames.service.dto.AllGamesDto;
import com.softserveinc.edu.boardgames.service.dto.GameDetailsDTO;

@RestController
public class GetGameDetailsController {
	@Autowired
	private GameService gameService;
	
	@RequestMapping(value="/getGameDetails/{name}", method = RequestMethod.GET)
	@ResponseBody
	public GameDetailsDTO getGameDetails(@PathVariable String name){
		return gameService.getGamesByName(name);
	}
}
