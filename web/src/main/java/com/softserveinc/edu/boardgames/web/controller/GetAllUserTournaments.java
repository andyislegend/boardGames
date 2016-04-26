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
import com.softserveinc.edu.boardgames.persistence.entity.TournamentComposition;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.GameUserMapper;
import com.softserveinc.edu.boardgames.service.GameUserService;
import com.softserveinc.edu.boardgames.service.TournamentCompositionService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@Controller
public class GetAllUserTournaments {
	@Autowired
	private TournamentCompositionService tournamentCompositionService;
	
	@RequestMapping(value = "/allUsersTournaments", method = RequestMethod.GET)
	@ResponseBody
	public List<TournamentComposition> showTournaments(@RequestParam("userName") String userName) {
		List<TournamentComposition> allTournaments = tournamentCompositionService.findByUsersUsername(userName);
		return allTournaments;
	}
}
