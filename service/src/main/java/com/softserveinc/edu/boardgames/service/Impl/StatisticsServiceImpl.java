package com.softserveinc.edu.boardgames.service.Impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserveinc.edu.boardgames.persistence.entity.dto.ActionsDTO;
import com.softserveinc.edu.boardgames.service.EventService;
import com.softserveinc.edu.boardgames.service.StatisticsService;
import com.softserveinc.edu.boardgames.service.TournamentService;

@Service
public class StatisticsServiceImpl implements StatisticsService{

	@Autowired
	private TournamentService tournamentService;
	
	@Autowired
	private EventService eventService;
	
	@Override
	public List<ActionsDTO> getActionsStatistics() {
		
		List<ActionsDTO> actionsSet = new ArrayList<>();
		Set<Date> dates = new TreeSet<>();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		dates.addAll(tournamentService.getAllTournamentsDates());
		dates.addAll(eventService.getAllDatesOfEvents());
		for (Date date : dates) {
			ActionsDTO actionsDto = new ActionsDTO();
			actionsDto.setDate(formatter.format(date).toString());
			actionsDto.setEvents(eventService.countEventsOnDate(date));
			actionsDto.setTournaments(tournamentService.countTournamentsOnDate(date));
			actionsSet.add(actionsDto);
		}
		return actionsSet;
	}
}
