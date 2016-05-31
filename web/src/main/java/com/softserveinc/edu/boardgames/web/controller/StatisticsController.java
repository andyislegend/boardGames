package com.softserveinc.edu.boardgames.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.dto.ActionsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GamesChartDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UsersAgeChartDTO;
import com.softserveinc.edu.boardgames.service.CategoryService;
import com.softserveinc.edu.boardgames.service.EventService;
import com.softserveinc.edu.boardgames.service.ExchangeService;
import com.softserveinc.edu.boardgames.service.GameService;
import com.softserveinc.edu.boardgames.service.GameUserService;
import com.softserveinc.edu.boardgames.service.NotificationService;
import com.softserveinc.edu.boardgames.service.TournamentService;

/**
 * @author Taras Varvariuk
 * Controller responsible for getting data 
 * to be displayed in charts
 */
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
	
	@Autowired
	NotificationService notificationService;
	
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
		Set<Date> dates = new TreeSet<>();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		dates.addAll(notificationService.getAllNotificationDates());
		dates.addAll(tournamentService.getAllTournamentsDates());
		dates.addAll(eventService.getAllDatesOfEvents());
		for (Date date : dates) {
			ActionsDTO actionsDto = new ActionsDTO();
			actionsDto.setDate(formatter.format(date).toString());
			actionsDto.setEvents(eventService.countEventsOnDate(date));
			actionsDto.setExchanges(notificationService.countNotificationsForSpecificDate(date));
			actionsDto.setTournaments(tournamentService.countTournamentsOnDate(date));
			actionsSet.add(actionsDto);
		}
		return actionsSet;
	}
	
	@RequestMapping(value="/getUsersAvgAge", method = RequestMethod.GET)
	@ResponseBody
	public List<UsersAgeChartDTO> countOfUsersOfAge() {
		return gameService.countOfUsersOfAge();
	}
}
