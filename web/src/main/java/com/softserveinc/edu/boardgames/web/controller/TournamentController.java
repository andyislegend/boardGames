package com.softserveinc.edu.boardgames.web.controller;

import com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentCreateDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.TournamentMapper;
import com.softserveinc.edu.boardgames.service.*;
import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
   
    @RequestMapping(value = "/tournaments")
    @ResponseBody
    public List<TournamentsDTO> getAllTournaments() {
    	return tournamentService.getAllTornaments();
    }
    
    @RequestMapping(value = "/tournament/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TournamentsDTO getTournamentById(@PathVariable Integer id){
    	return tournamentService.getTournamentById(id);
    }
    
    @RequestMapping(value = "/joinToTournament/{tournamentId}", method = RequestMethod.PUT)
    public void joinToTournament(@PathVariable Integer tournamentId){
    	tournamentService.addParticipantToTournament(tournamentId, userService.getUser(WebUtil.getPrincipalUsername()).getId());
    }
    
    @RequestMapping(value = "/leaveTournament/{tournamentId}", method = RequestMethod.PUT)
    public void leaveTournament(@PathVariable Integer tournamentId){
    	tournamentService.deleteParticipantsFromTournamnet(tournamentId, userService.getUser(WebUtil.getPrincipalUsername()).getId());
    }
    
    @RequestMapping(value = "/addTournament", method = RequestMethod.POST)
    public void addTournament(@RequestBody TournamentCreateDTO tournamentDTO) {
        Tournament tournament = TournamentMapper.toEntity(tournamentDTO);
        tournament.setUserCreator(userService.getUser(WebUtil.getPrincipalUsername()));
        tournament.setGame(gameUserService.getUserGamesById(tournamentDTO.getGameId()));
        tournamentService.save(tournament);
    }
    
    @RequestMapping(value = "/deleteTournament/{tournamentId}", method = RequestMethod.DELETE)
    public void deleteTournament(@PathVariable Integer tournamentId) {
    	tournamentService.deleteTournament(tournamentId);
    }
      
    @RequestMapping(value = "/getAllParticipants/{tournamentId}", method = RequestMethod.GET)
    @ResponseBody
    public Set<User> getParticipantOfTournament(@PathVariable Integer tournamentId) {
    	Tournament tournament = tournamentService.getTournamenById(tournamentId);
    	return tournament.getUsers();
    }
    
    @RequestMapping(value = "/getCurentUser", method = RequestMethod.GET)
    @ResponseBody
    public User getCurrentUser(){
    	return userService.getUser(WebUtil.getPrincipalUsername());
    }
    
    @RequestMapping(value = "/giveUser/{idUser}/rate/{rate}", method = RequestMethod.PUT)
    public void giveRateToUser(@PathVariable Integer idUser, @PathVariable Integer rate) {
    	User user = userService.findById(idUser);
    	user.setUserRating(user.getUserRating()+rate);
    	user.setTournamentRatingStatus(true);
    	userService.updateUserWithBan(user);
    }
    
    @RequestMapping(value = "/updateDateOfTournament/{date}/{tournamentId}", method = RequestMethod.PUT)
    public void updateDateOfTournamnets(@PathVariable("date") Date date, @PathVariable("tournamentId")Integer tournamentId){
    	Tournament tournament = tournamentService.getTournamenById(tournamentId);
    	tournament.setDateOfTournament(date);
    	tournamentService.update(tournament);
    }

    @RequestMapping(value = "/getAllTournamentsByCreator", method = RequestMethod.GET)
    @ResponseBody
    public List<TournamentsDTO> getAllTournamentsByCreator(){
    	return tournamentService.getAllTournamentByUserCreator(userService.getUser(WebUtil.getPrincipalUsername()).getUsername());
    }
}
