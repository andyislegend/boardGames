package com.softserveinc.edu.boardgames.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO;
import com.softserveinc.edu.boardgames.persistence.repository.TournamentRepository;

/**
 * @author Volodymyr Krokhmalyuk
 * @since 12.04.2016
 */
@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;
    
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void save(Tournament tournament){
        tournamentRepository.save(tournament);
    }

    @Transactional
    public void update(Tournament tournament){
        tournamentRepository.saveAndFlush(tournament);
    }

    @Transactional
    public void delete(Tournament tournament){
        tournamentRepository.delete(tournament);
    }
    
    /**
     * This method add user for tournament
     * 
     * @param tournamentId
     * @param userId
     */
    @Transactional
    @Modifying
    public void addParticipantToTournament(Integer tournamentId, Integer userId){
    	Query query = entityManager.createNativeQuery("INSERT INTO tournament_users(Tournament_id, users_id) VALUES (?,?)");
    	query.setParameter(1, tournamentId);
    	query.setParameter(2, userId);
    	query.executeUpdate();
    	
    }
    
    /**
     * This method delete user from tournament
     * 
     * @param tournamentId
     * @param userId
     */
    @Transactional
    @Modifying
    public void deleteParticipantsFromTournamnet(Integer tournamentId, Integer userId){
    	Query query = entityManager.createNativeQuery("DELETE FROM tournament_users WHERE `Tournament_id`=? and`users_id`=?");
    	query.setParameter(1, tournamentId);
    	query.setParameter(2, userId);
        query.executeUpdate();
    }
    
    /**
     * This method delete tournament by id
     * 
     * @param id
     */
    @Transactional
    public void deleteTournament(Integer id){
    	 tournamentRepository.deleteTournament(id);
    }
    
    /**
     * This method get List of tournaments
     * @return
     */
    public List<TournamentsDTO> getAllTornaments() {
    	return  tournamentRepository.getAllTournaments();
    }
    
    /**
     * This method get tournament by od
     * 
     * @param id
     * @return
     */
    public Tournament getTournamenById(Integer id){
    	return tournamentRepository.findById(id);
    }
    
    /**
     * This method get all tournaments
     * 
     * @return tournaments
     */
    public List<Tournament> findAll(){
        return tournamentRepository.findAll();
    }
    
    /**
     * This method get tournament by id
     * 
     * @param id
     * @return
     */
    public TournamentsDTO getTournamentById(Integer id) {
    	return tournamentRepository.getTournamentsById(id);
    }
    /**
     * This methiod get all by Word
     * 
     * @param name
     * @return
     */
    public List<TournamentsDTO> getTournamentsByWord(String name){
    	return tournamentRepository.findAllTournamentsByWord(name);
    }
    
    /**
     * This method get all tournaments by creator
     * 
     * @param username
     * @return
     */
    public List<TournamentsDTO> getAllTournamentByUserCreator(String username) {
    	return tournamentRepository.getAllTournamentsByUserCreator(username);
    }
    
    /**
     *@author Vasyl Bervetskyy
     **/
    public List<Tournament> getAllTommorowTournament(){
    	return tournamentRepository.getAllTommorowTournament();
    }
    
    public List<Date> getAllTournamentsDates() {
    	List<Date> dates = new ArrayList<>();
    	for (TournamentsDTO t: tournamentRepository.getTournamentDate()) {
    		dates.add(t.getDateOfTournament());
    	}
    	return dates;
    }
    
    public Integer countTournamentsOnDate(Date date) {
    	return tournamentRepository.countTournamentForSpecificDate(date);
    }
}
