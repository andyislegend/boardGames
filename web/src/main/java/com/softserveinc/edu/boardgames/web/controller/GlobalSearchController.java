package com.softserveinc.edu.boardgames.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.dto.AllFilesDTO;
import com.softserveinc.edu.boardgames.service.EventService;
import com.softserveinc.edu.boardgames.service.GameUserService;
import com.softserveinc.edu.boardgames.service.TournamentService;

@Controller
public class GlobalSearchController {
	
	@Autowired
	private GameUserService gameUserService;
	
	@Autowired
	private TournamentService tournamentService;
	
	@Autowired
	private EventService eventsService;

	@RequestMapping(value = "/searchBy/{keyWord}", method = RequestMethod.GET)
	@ResponseBody
	public AllFilesDTO getResultByGlobalSearch(@PathVariable String keyWord){
		AllFilesDTO allFilesDTO = new AllFilesDTO();
//		allFilesDTO.setGameUsers(gameUserService.getGameUsersByName(keyWord));
		allFilesDTO.setTournaments(tournamentService.getTournamentsByWord(keyWord));
		allFilesDTO.setEvents(eventsService.getAllEventsByName(keyWord));
		return allFilesDTO;
}
}
