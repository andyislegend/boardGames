package com.softserveinc.edu.boardgames.service.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.service.EventService;
import com.softserveinc.edu.boardgames.service.NotificationService;
import com.softserveinc.edu.boardgames.service.TournamentService;

@Component
public class NotificationListener {
	
	@Autowired
	TournamentService tournamentService;
	
	@Autowired
	EventService eventService;
	
	@Autowired
	NotificationService notificationService;
	
	@Scheduled(fixedRate = 24*60*60*1000)
	public void setTournamentNotification(){
		List<Tournament> listOfTournament = tournamentService.getAllTommorowTournament();
		notificationService.addTournamentNotification(listOfTournament);
	}
	
}
