package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.dto.AllGamesDto;
import com.softserveinc.edu.boardgames.persistence.entity.Category;
import com.softserveinc.edu.boardgames.persistence.entity.Event;
import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.service.CategoryService;
import com.softserveinc.edu.boardgames.service.EventService;
import com.softserveinc.edu.boardgames.service.GameService;

@RestController
public class GetAllGamesController {
	
	@Autowired
	private GameService gameService;
	
	private List<AllGamesDto> gamesTransfer = new ArrayList<AllGamesDto>();
	
	@RequestMapping(value="/getAllGames", method = RequestMethod.GET)
	@ResponseBody
	public List<AllGamesDto> getAllGames(){
		
		for (Game game : gameService.getAll()){
			AllGamesDto gamesDto = new AllGamesDto();
			gamesDto.setName(game.getName());
			gamesDto.setDescription(game.getDescription());
			gamesDto.setMinPlayers(game.getMinPlayers());
			gamesDto.setMaxPlayers(game.getMaxPlayers());
			gamesDto.setCategoryName(game.getCategory().getName());
			gamesDto.setRating(game.getGameRating());
			gamesTransfer.add(gamesDto);
		}
		return gamesTransfer;
	}

}
