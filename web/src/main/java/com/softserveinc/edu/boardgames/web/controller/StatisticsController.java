package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.dto.ActionsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GamesChartDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UsersAgeChartDTO;
import com.softserveinc.edu.boardgames.service.GameService;
import com.softserveinc.edu.boardgames.service.StatisticsService;

/**
 * @author Taras Varvariuk
 * Controller responsible for getting data 
 * to be displayed in charts
 */
@RestController
public class StatisticsController {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private StatisticsService statisticsService;
	
	@RequestMapping(value="/groupGamesByGameUsers", method = RequestMethod.GET)
	@ResponseBody
	public List<GamesChartDTO> groupGamesByGameUsers() {
		return gameService.groupGameUserByGame();
	}
	
	@RequestMapping(value="/getGamesToRatings", method = RequestMethod.GET)
	@ResponseBody
	public List<GamesChartDTO> getGamesToRatings() {
		return gameService.getRatingsForGame();
	}
	
	@RequestMapping(value="/getCountOfActions", method = RequestMethod.GET)
	@ResponseBody
	public List<ActionsDTO> getCountOfActions() {
		
		return statisticsService.getActionsStatistics();
	}
	
	@RequestMapping(value="/getUsersAvgAge", method = RequestMethod.GET)
	@ResponseBody
	public List<UsersAgeChartDTO> countOfUsersOfAge() {
		return gameService.countOfUsersOfAge();
	}
}
