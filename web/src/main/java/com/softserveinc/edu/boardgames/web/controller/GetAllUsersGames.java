package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.service.GameUserService;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;

/**
 * Controller for finding all games that belong to some user.
 * 
 * @author Volodymyr Krokhmaliuk, Volodymyr Terlyha
 *
 */
@Controller
public class GetAllUsersGames {

	@Autowired
	GameUserService gameUserService;

	/**
	 * Returns needed information about games that user owns.
	 *
	 * @param userName
	 *            username of user, who's games we want to find
	 */
	@RequestMapping(value = {"/allUsersGames"}, method = RequestMethod.GET)
	@ResponseBody
	public List<GameUserDTO> showGames(@RequestParam("username") String username) {
		List<GameUserDTO> allGames = gameUserService.getGameUsersFromUsername(username);
		return allGames;
	}
}
