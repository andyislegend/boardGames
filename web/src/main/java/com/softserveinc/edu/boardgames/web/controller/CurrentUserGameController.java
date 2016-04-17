package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.softserveinc.edu.boardgames.persistence.entity.Category;
import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.GameRating;

/**
 * 
 * @author Volodymyr Krokhmaliuk
 *
 */
@Controller
public class CurrentUserGameController {

	@RequestMapping(value = "/getAllGamesCurUser", method = RequestMethod.GET)
	@ResponseBody
	public List showGames() {
		Game game1 = new Game();
		  game1.setName("chess");
		  game1.setCategory(new Category("strategic"));
		  game1.setDescription("bla bla");
		  game1.setMinPlayers(2);
		  game1.setMaxPlayers(2);
		  GameRating rating1 = new GameRating();
		  rating1.setRating(28.5);
		  game1.setGameRating(rating1);
		  
		  Game game2 = new Game();
		  game2.setName("gameOfThrones");
		  game2.setCategory(new Category("strategic"));
		  game2.setDescription("die");
		  game2.setMinPlayers(2);
		  game2.setMaxPlayers(8);
		  GameRating rating2 = new GameRating();
		  rating2.setRating(18.3);
		  game2.setGameRating(rating2);
		  
		  List<Game> someGames = new ArrayList<Game>();
		  someGames.add(game1);
		  someGames.add(game2);
		return someGames;
	}

	/**
	 * 
	 * Method for returning user login
	 * 
	 * @return user login name
	 */
	private String getUserLogin() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}
