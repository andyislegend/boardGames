package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.dto.ChartDTO;
import com.softserveinc.edu.boardgames.service.CategoryService;
import com.softserveinc.edu.boardgames.service.ExchangeService;
import com.softserveinc.edu.boardgames.service.GameService;
import com.softserveinc.edu.boardgames.service.GameUserService;

@RestController
public class StatisticsController {

	@Autowired
	GameUserService gameUserService;
	
	@Autowired
	GameService gameService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ExchangeService exchangeService;
	
	@RequestMapping(value="/groupGamesByGameUsers", method = RequestMethod.GET)
	@ResponseBody
	public List<ChartDTO> groupGamesByGameUsers() {
		return gameService.groupGameUserByGame();
	}
	
}
