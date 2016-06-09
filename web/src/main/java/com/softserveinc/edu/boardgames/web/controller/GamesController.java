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
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllGamesDto;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameDetailsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserGamesOfGameDTO;
import com.softserveinc.edu.boardgames.service.GameRatingService;
import com.softserveinc.edu.boardgames.service.GameService;
import com.softserveinc.edu.boardgames.service.GameUserService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

/**
 * @author Taras Varvariuk
 * Simple controller performs getting all games
 * Controller getting game of instance
 * getting and displaying rating
 * calculating averege rating
 */
@RestController
public class GamesController {
	
	final int DEFAULT_RATING = 0;
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private GameUserService gameUserService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GameRatingService gameRateNumService;
	
	@RequestMapping(value="/getAllGames", method = RequestMethod.GET)
	@ResponseBody
	public List<AllGamesDto> getAllGames(){
		return gameService.getGamesDTO();
	}
	
	@RequestMapping(value="/getGameDetails/{gameId}", method = RequestMethod.GET)
	@ResponseBody
	public GameDetailsDTO getGameDetails(@PathVariable Integer gameId){
		GameDetailsDTO gameDetails = new GameDetailsDTO();
		gameDetails.setName(gameService.findById(gameId).getName());
		gameDetails.setUserRating(gameRateNumService.getForGameAndUser(gameId, 
				userService.findOne(WebUtil.getPrincipalUsername()).getId()));
		if (gameDetails.getUserRating() == null)
			gameDetails.setUserRating(new Double(DEFAULT_RATING));
		gameDetails.setGeneralRating(gameRateNumService.getAverageRating(gameId));
		if (gameDetails.getGeneralRating() == null)
			gameDetails.setGeneralRating(new Double(DEFAULT_RATING));
		return gameDetails;
	}
	
	@RequestMapping(value="/getUserGamesOfGame/{name}", method = RequestMethod.GET)
	@ResponseBody
	public List<UserGamesOfGameDTO> getGamesOfInstance(@PathVariable String name) {
		return gameUserService.getAllUserGamesOfGame(name);
	}
	
	@RequestMapping(value="/calculateRatings/{gameId}/{rating}", method = RequestMethod.POST)
	@ResponseBody
	public void reCalculateRaings(@PathVariable Integer gameId, @PathVariable Integer rating) {
		User curUser = userService.findOne(WebUtil.getPrincipalUsername());
		if (gameRateNumService.checkIfUserRated(gameId, curUser.getId())) {
			gameRateNumService.deleteCustom(gameId, curUser.getId());;
		}
		GameRating gameRating = new GameRating();
		gameRating.setRating(rating);
		gameRating.setGame(gameService.findById(gameId));
		gameRating.setUser(curUser);
		gameRateNumService.update(gameRating);
	}
}