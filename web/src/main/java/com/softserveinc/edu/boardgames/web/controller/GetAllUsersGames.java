package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.service.GameUserService;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.service.mapper.GameUserMapper;


@Controller
public class GetAllUsersGames {
	
	@Autowired
	GameUserService gameUserService;
	
	@RequestMapping(value = {"/allUsersGames"}, method = RequestMethod.GET)
	@ResponseBody

	public List<GameUserDTO> showGames(@RequestParam("userName") String userName) {
		List<GameUser> allGames = gameUserService.getGameUsersFromUsername(userName);
		List<GameUserDTO> gameUserDTOs = new ArrayList<>();
		for(GameUser dto : allGames){
			gameUserDTOs.add(new GameUserMapper().toDTO(dto));
		}
		return gameUserDTOs;
	}
}
