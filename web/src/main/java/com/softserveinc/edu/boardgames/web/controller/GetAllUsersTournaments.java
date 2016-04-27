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

/**
 * Controller for finding all tournaments that belong to some user.
 * 
 * @author Volodymyr Terlyha
 *
 */
@Controller
public class GetAllUsersTournaments {

	@Autowired
	TournamentService tournamentService;

	/**
	 * Returns needed information about tournaments that user took part.
	 *
	 * @param userName
	 *            username of user, who's tournaments we want to find
	 */
	@RequestMapping(value = {"/allUsersTournaments"}, method = RequestMethod.GET)
	@ResponseBody
	public List<AllTournamentsDTO> showGames(@RequestParam("username") String username) {
		List<AllTournamentsDTO> allGames = tournamentService.findTournamentsByUserName(username);
		System.out.println(allGames);
		return allGames;
	}
}