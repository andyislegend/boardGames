package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.GameRating;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameDetailsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserGamesOfGameDTO;
import com.softserveinc.edu.boardgames.service.GameRatingService;
import com.softserveinc.edu.boardgames.service.GameService;
import com.softserveinc.edu.boardgames.service.GameUserService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@RestController
public class GetGameDetailsController {
	
	final int DEFAULT_RATING = 0;
	@Autowired
	private GameService gameService;
	
	@Autowired
	private GameUserService gameUserService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GameRatingService gameRateNumService;
	
	@RequestMapping(value="/getGameDetails/{gameId}", method = RequestMethod.GET)
	@ResponseBody
	public GameDetailsDTO getGameDetails(@PathVariable Integer gameId){
		
		GameDetailsDTO gameDetailsDTO = gameService.getGamesById(gameId);
		return gameDetailsDTO;
	}
	
	@RequestMapping(value="/getUserGamesOfGame/{name}", method = RequestMethod.GET)
	@ResponseBody
	public List<UserGamesOfGameDTO> getGamesOfInstance(@PathVariable String name) {
		return gameUserService.getAllUserGamesOfGame(name);
	}
	
	@RequestMapping(value="/getGameRatedByUser/{gameId}", method = RequestMethod.GET)
	@ResponseBody
	public Integer getGameRatedByUser(@PathVariable Integer gameId) {
		User user = userService.getUser(WebUtil.getPrincipalUsername());
		Integer rating = gameRateNumService.getRatingforUser(gameId, user.getId());
		if (rating == null)
			rating = new Integer(DEFAULT_RATING);
		return rating;
	}
	
	@RequestMapping(value="/calculateRatings/{gameId}/{rating}", method = RequestMethod.POST)
	@ResponseBody
	public void reCalculateRaings(@PathVariable Integer gameId, @PathVariable Integer rating) {
		GameRating existingRating = gameRateNumService.getFromGame(gameId);
		if (existingRating != null) {
			existingRating.setRating(rating);
		}else {
			existingRating = new GameRating();
			User user = userService.getUser(WebUtil.getPrincipalUsername());
			existingRating.setGame(gameService.findById(gameId));
			existingRating.setRating(rating);
			existingRating.setUser(user);
		}		
		gameRateNumService.update(existingRating);
	}
}