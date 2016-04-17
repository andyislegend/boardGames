package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.softserveinc.edu.boardgames.dto.AllGamesForCurrentUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.Category;
import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.repository.GameUserRepository;

/**
 * 
 * @author Volodymyr Krokhmaliuk
 *
 */
@Controller
public class CurrentUserGameController {

	@Autowired
	private GameUserRepository gameUserRep;
	
	@RequestMapping(value = "/getAllGamesCurUser", method = RequestMethod.GET)
	@ResponseBody
	public List showGames() {
		List<AllGamesForCurrentUserDTO> games = new ArrayList<>();
		AllGamesForCurrentUserDTO allGamesForCurrentUserDTO = new AllGamesForCurrentUserDTO();
		for(Game game : gameUserRep.getAllGamesForCurrentUser(getUserLogin())){
			allGamesForCurrentUserDTO.setGameName(game.getName());
			allGamesForCurrentUserDTO.setCategoryName(game.getCategory().getName());
			games.add(allGamesForCurrentUserDTO);
		}
		return games;
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
