package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.dto.ActionsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GamesChartDTO;
import com.softserveinc.edu.boardgames.service.CategoryService;
import com.softserveinc.edu.boardgames.service.EventService;
import com.softserveinc.edu.boardgames.service.ExchangeService;
import com.softserveinc.edu.boardgames.service.GameService;
import com.softserveinc.edu.boardgames.service.GameUserService;
import com.softserveinc.edu.boardgames.service.TournamentService;

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
	
	@Autowired
	TournamentService tournamentService;
	
	@Autowired
	EventService eventService;
	
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
		List<ActionsDTO> actionsSet = new ArrayList<>();
		Set<Date> dates = new HashSet<>();
//		dates.addAll(exchangeService.getAllExchangeDates());
//		dates.addAll(tournamentService.getAllTournamentsDates());
//		dates.addAll(eventService.getAllDatesOfEvents());
		exchangeService.getAllExchangeDates();
		tournamentService.getAllTournamentsDates();
		eventService.getAllDatesOfEvents();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
				+ "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1" + dates);
		return actionsSet;
	}
	
}
