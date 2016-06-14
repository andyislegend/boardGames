package com.softserveinc.edu.boardgames.web.controller;

import com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentCreateDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.TournamentMapper;
import com.softserveinc.edu.boardgames.service.*;
import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> joinToTournament(@PathVariable Integer tournamentId){
    	tournamentService.addParticipantToTournament(tournamentId, userService.getUser(WebUtil.getPrincipalUsername()).getId());
    	return new ResponseEntity<String>(new Tournament().toString(), HttpStatus.OK);
    }
    
    /**
     * This method delete user from tournament
     * 
     * @param tournamentId
     */
    @RequestMapping(value = "/leaveTournament/{tournamentId}", method = RequestMethod.PUT)
    public ResponseEntity<String> leaveTournament(@PathVariable Integer tournamentId){
    	tournamentService.deleteParticipantsFromTournamnet(tournamentId, userService.getUser(WebUtil.getPrincipalUsername()).getId());
    	return new ResponseEntity<String>(new Tournament().toString(), HttpStatus.OK);
    	   
    }
    
    /**
     * This method add new tournament
     * 
     * @param tournamentDTO
     */
    @RequestMapping(value = "/addTournament", method = RequestMethod.POST)
    public ResponseEntity<String> addTournament(@RequestBody TournamentCreateDTO tournamentDTO) {
        Tournament tournament = TournamentMapper.toEntity(tournamentDTO);
        tournament.setCity(cityService.findById(tournamentDTO.getCityId()));
        tournament.setCountry(countryService.findById(tournamentDTO.getCountryId()));
        tournament.setUserCreator(userService.getUser(WebUtil.getPrincipalUsername()));
        tournament.setGame(gameUserService.getUserGamesById(tournamentDTO.getGameId()));
        Set<User> users = new HashSet<>();
        User user = userService.getUser(WebUtil.getPrincipalUsername());
        users.add(user);
        tournament.setUsers(users);
        tournamentService.save(tournament);
        return new ResponseEntity<String>(new Tournament().toString(), HttpStatus.OK);
        
    }
    
    /**
     * This method delete tournament
     * 
     * @param tournamentId
     */
    @RequestMapping(value = "/deleteTournament/{tournamentId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTournament(@PathVariable Integer tournamentId) {
    	tournamentService.deleteTournament(tournamentId);
    	return new ResponseEntity<String>(new Tournament().toString(), HttpStatus.OK);
        
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
    public ResponseEntity<String> giveRateForAddToSystem(@PathVariable Integer mark) {
    	User user = userService.findById(userService.getUser(WebUtil.getPrincipalUsername()).getId());
    	user.setUserRating(user.getUserRating()+mark);
    	userService.updateUser(user);
    	return new ResponseEntity<String>(new Tournament().toString(), HttpStatus.OK);
        
    }
    
    /**
     * This method give rate to user
     * 
     * @param idUser
     * @param rate
     */
    @RequestMapping(value = "/giveUser/{idUser}/rate/{rate}", method = RequestMethod.PUT)
    public ResponseEntity<String> giveRateToUser(@PathVariable Integer idUser, @PathVariable Integer rate) {
    	User user = userService.findById(idUser);
    	user.setUserRating(user.getUserRating()+rate);
    	user.setTournamentRatingStatus(true);
    	userService.updateUserWithBan(user);
    	return new ResponseEntity<String>(new Tournament().toString(), HttpStatus.OK);
    }
    
    /**
     * This method change status of table
     * 
     * @param tournamentId
     */
    @RequestMapping(value = "/generateTournamentTable/{tournamentId}",method = RequestMethod.PUT)
    public ResponseEntity<String> generateTournamentTable(@PathVariable Integer tournamentId){
    	Tournament tournament = tournamentService.getTournamenById(tournamentId);
    	tournament.setTableGenerated(true);
    	tournamentService.update(tournament);
    	return new ResponseEntity<String>(new Tournament().toString(), HttpStatus.OK);
    } 
    
    /**
     * This method update date of tournament
     * @param date
     * @param tournamentId
     */
    @RequestMapping(value = "/updateDateOfTournament/{date}/{tournamentId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateDateOfTournamnets(@PathVariable("date") Date date, @PathVariable("tournamentId")Integer tournamentId){
    	Tournament tournament = tournamentService.getTournamenById(tournamentId);
    	tournament.setDateOfTournament(date);
    	tournamentService.update(tournament);
    	return new ResponseEntity<String>(new Tournament().toString(), HttpStatus.OK);
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
