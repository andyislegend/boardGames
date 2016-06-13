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
    
    @Autowired
    private CountryService countryService;
    
    @Autowired
    private CityService cityService;
   
    /**
     * This method return all tournaments
     * 
     * @return list of tournaments
     */
    @RequestMapping(value = "/tournaments")
    @ResponseBody
    public List<TournamentsDTO> getAllTournaments() {
    	return tournamentService.getAllTornaments();
    }
    
    /**
     *This method get tournaments by id
     * 
     * @param id
     * 
     */
    @RequestMapping(value = "/tournament/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TournamentsDTO getTournamentById(@PathVariable Integer id){
    	return tournamentService.getTournamentById(id);
    }
    
    /**
     * This method add user to tournament
     * 
     * @param tournamentId
     */
    @RequestMapping(value = "/joinToTournament/{tournamentId}", method = RequestMethod.PUT)
    public void joinToTournament(@PathVariable Integer tournamentId){
    	tournamentService.addParticipantToTournament(tournamentId, userService.getUser(WebUtil.getPrincipalUsername()).getId());
    }
    
    /**
     * This method delete user from tournament
     * 
     * @param tournamentId
     */
    @RequestMapping(value = "/leaveTournament/{tournamentId}", method = RequestMethod.PUT)
    public void leaveTournament(@PathVariable Integer tournamentId){
    	tournamentService.deleteParticipantsFromTournamnet(tournamentId, userService.getUser(WebUtil.getPrincipalUsername()).getId());
    }
    
    /**
     * This method add new tournament
     * 
     * @param tournamentDTO
     */
    @RequestMapping(value = "/addTournament", method = RequestMethod.POST)
    public void addTournament(@RequestBody TournamentCreateDTO tournamentDTO) {
        Tournament tournament = TournamentMapper.toEntity(tournamentDTO);
        tournament.setCity(cityService.findById(tournamentDTO.getCityId()));
        tournament.setCountry(countryService.findById(tournamentDTO.getCountryId()));
        tournament.setUserCreator(userService.getUser(WebUtil.getPrincipalUsername()));
        tournament.setGame(gameUserService.getUserGamesById(tournamentDTO.getGameId()));
        tournamentService.save(tournament);
    }
    
    /**
     * This method delete tournament
     * 
     * @param tournamentId
     */
    @RequestMapping(value = "/deleteTournament/{tournamentId}", method = RequestMethod.DELETE)
    public void deleteTournament(@PathVariable Integer tournamentId) {
    	tournamentService.deleteTournament(tournamentId);
    }
     
    /**
     * This method get all participants
     * 
     * @param tournamentId
     * @return
     */
    @RequestMapping(value = "/getAllParticipants/{tournamentId}", method = RequestMethod.GET)
    @ResponseBody
    public Set<User> getParticipantOfTournament(@PathVariable Integer tournamentId) {
    	Tournament tournament = tournamentService.getTournamenById(tournamentId);
    	return tournament.getUsers();
    }
    
    /**
     * This method get Current user
     * 
     * @return
     */
    @RequestMapping(value = "/getCurentUser", method = RequestMethod.GET)
    @ResponseBody
    public User getCurrentUser(){
    	return userService.getUser(WebUtil.getPrincipalUsername());
    }
    
    /**
     * This method give a mark to user
     * 
     * @param mark
     */
    @RequestMapping(value = "giveRate/{mark}", method = RequestMethod.PUT)
    public void giveRateForAddToSystem(@PathVariable Integer mark) {
    	User user = userService.findById(userService.getUser(WebUtil.getPrincipalUsername()).getId());
    	user.setUserRating(user.getUserRating()+mark);
    	userService.updateUser(user);
    }
    
    /**
     * This method give rate to user
     * 
     * @param idUser
     * @param rate
     */
    @RequestMapping(value = "/giveUser/{idUser}/rate/{rate}", method = RequestMethod.PUT)
    public void giveRateToUser(@PathVariable Integer idUser, @PathVariable Integer rate) {
    	User user = userService.findById(idUser);
    	user.setUserRating(user.getUserRating()+rate);
    	user.setTournamentRatingStatus(true);
    	userService.updateUserWithBan(user);
    }
    
    /**
     * This method change status of table
     * 
     * @param tournamentId
     */
    @RequestMapping(value = "/generateTournamentTable/{tournamentId}",method = RequestMethod.PUT)
    public void generateTournamentTable(@PathVariable Integer tournamentId){
    	Tournament tournament = tournamentService.getTournamenById(tournamentId);
    	tournament.setTableGenerated(true);
    	tournamentService.update(tournament);
    } 
    
    /**
     * This method update date of tournament
     * @param date
     * @param tournamentId
     */
    @RequestMapping(value = "/updateDateOfTournament/{date}/{tournamentId}", method = RequestMethod.PUT)
    public void updateDateOfTournamnets(@PathVariable("date") Date date, @PathVariable("tournamentId")Integer tournamentId){
    	Tournament tournament = tournamentService.getTournamenById(tournamentId);
    	tournament.setDateOfTournament(date);
    	tournamentService.update(tournament);
    }
    
    /**
     * This method get all tournamnets by creator
     * @return
     */
    @RequestMapping(value = "/getAllTournamentsByCreator", method = RequestMethod.GET)
    @ResponseBody
    public List<TournamentsDTO> getAllTournamentsByCreator(){
    	return tournamentService.getAllTournamentByUserCreator(userService.getUser(WebUtil.getPrincipalUsername()).getUsername());
    }
}
