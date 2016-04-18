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
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.repository.GameRepository;
import com.softserveinc.edu.boardgames.persistence.repository.GameUserRepository;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

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
	public List<AllGamesForCurrentUserDTO> showGames() {
		List<AllGamesForCurrentUserDTO> games = new ArrayList<>();
		List<GameUser> allGames = gameUserRep.getAllGamesForCurrentUser(WebUtil.getPrincipalUsername());
		for(GameUser game : allGames ){
			AllGamesForCurrentUserDTO allGamesForCurrentUserDTO = new AllGamesForCurrentUserDTO();
			allGamesForCurrentUserDTO.setName(game.getGame().getName());
			allGamesForCurrentUserDTO.setCategory(game.getGame().getCategory().getName());
			games.add(allGamesForCurrentUserDTO);
		}
		return games;
	}
}
