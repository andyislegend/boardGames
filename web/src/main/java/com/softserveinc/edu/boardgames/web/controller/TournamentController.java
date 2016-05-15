package com.softserveinc.edu.boardgames.web.controller;

import com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.TournamentMapper;
import com.softserveinc.edu.boardgames.service.*;
import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.web.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Volodymyr Krokhmaliuk
 */

@Controller
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private GameUserService gameUserService;
   
    @RequestMapping(value = "/addTournament", method = RequestMethod.POST)
    public void addTournament(@RequestBody TournamentsDTO tournamentDTO) {
        Tournament tournament = TournamentMapper.toEntity(tournamentDTO);
        tournament.setUserCreator(userService.getUser(WebUtil.getPrincipalUsername()));
        tournament.setGame(gameUserService.getUserGamesById(tournamentDTO.getGameUserId()));
        tournamentService.save(tournament);
    }
}
