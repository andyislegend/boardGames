package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.dto.AllTournamentsDTO;
import com.softserveinc.edu.boardgames.service.TournamentService;

@Controller
public class GetAllUsersTournaments {
	
	@Autowired
	TournamentService tournamentService;
	
	@RequestMapping(value = {"/allUsersTournaments"}, method = RequestMethod.GET)
	@ResponseBody
	public List<AllTournamentsDTO> showGames(@RequestParam("username") String username) {
		List<AllTournamentsDTO> allGames = tournamentService.findTournamentsByUserId(username);
		return allGames;
	}
}
