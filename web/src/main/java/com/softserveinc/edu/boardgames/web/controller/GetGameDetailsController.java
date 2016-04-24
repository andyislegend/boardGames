package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.GameRatingNumeric;
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllGamesDto;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameDetailsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserGamesOfGameDTO;
import com.softserveinc.edu.boardgames.service.GameRatingNumericService;
import com.softserveinc.edu.boardgames.service.GameService;
import com.softserveinc.edu.boardgames.service.GameUserService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@RestController
public class GetGameDetailsController {
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private GameUserService gameUserService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GameRatingNumericService gameRateNumService;
	
	@RequestMapping(value="/getGameDetails/{gameId}", method = RequestMethod.GET)
	@ResponseBody
	public GameDetailsDTO getGameDetails(@PathVariable Integer gameId){
		return gameService.getGamesById(gameId);
	}
	
	@RequestMapping(value="/getUserGamesOfGame/{name}", method = RequestMethod.GET)
	@ResponseBody
	public List<UserGamesOfGameDTO> getGamesOfInstance(@PathVariable String name){
		return gameUserService.getAllUserGamesOfGame(name);
	}
	
	@RequestMapping(value="/calculateRatings/{gameId}/{rating}", method = RequestMethod.POST)
	@ResponseBody
	public void reCalculateRaings(@PathVariable Integer gameId, @PathVariable Integer rating){
		Game game = gameService.findById(gameId);
		User user = userService.getUser(WebUtil.getPrincipalUsername());
		GameRatingNumeric gameRating = new GameRatingNumeric();
		gameRating.setIsRated(true);
		gameRating.setRating(rating);
		gameRating.setUser(user);
		gameRating.setGame(game);
		gameRateNumService.update(gameRating);
	}
}
