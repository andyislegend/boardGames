package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.repository.GameRepository;
import com.softserveinc.edu.boardgames.persistence.repository.GameUserRepository;
import com.softserveinc.edu.boardgames.service.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.service.mapper.GameUserMapper;
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
	public List<GameUserDTO> showGames() {
		List<GameUser> allGames = gameUserRep.getAllGamesForCurrentUser(WebUtil.getPrincipalUsername());
		List<GameUserDTO> gameUserDTOs = new ArrayList<>();
		for(GameUser dto : allGames){
			gameUserDTOs.add(new GameUserMapper().toDTO(dto));
		}
		return gameUserDTOs;
	}
	
	@RequestMapping(value = "addNewGame", method = RequestMethod.POST)
	public String addNewGame(Model model){
		return "";
	}
}
