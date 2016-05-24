package com.softserveinc.edu.boardgames.service;

import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO;
import com.softserveinc.edu.boardgames.persistence.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
    
    @Transactional
    @Modifying
    public void addParticipantToTournament(Integer tournamentId, Integer userId){
    	Query query = entityManager.createNativeQuery("INSERT INTO tournament_users(Tournament_id, users_id) VALUES (?,?)");
    	query.setParameter(1, tournamentId);
    	query.setParameter(2, userId);
    	query.executeUpdate();
    	
    }
    
    @Transactional
    @Modifying
    public void deleteParticipantsFromTournamnet(Integer tournamentId, Integer userId){
    	Query query = entityManager.createNativeQuery("DELETE FROM tournament_users WHERE `Tournament_id`=? and`users_id`=?");
    	query.setParameter(1, tournamentId);
    	query.setParameter(2, userId);
        query.executeUpdate();
    }
    
    public List<TournamentsDTO> getAllTornaments() {
    	return  tournamentRepository.getAllTournaments();
    }

    public Tournament getTournamenById(Integer id){
    	return tournamentRepository.findById(id);
    }
    
    public List<Tournament> findAll(){
        return tournamentRepository.findAll();
    }
    
    public TournamentsDTO getTournamentById(Integer id) {
    	return tournamentRepository.getTournamentsById(id);
    }
    
    public List<TournamentsDTO> getTournamentsByWord(String name){
    	return tournamentRepository.findAllTournamentsByWord(name);
    }
    
    public List<TournamentsDTO> getAllTournamentByUserCreator(String username) {
    	return tournamentRepository.getAllTournamentsByUserCreator(username);
    }
    
    /**
     *@author Vasyl Bervetskyy
     **/
    public List<Object[]> getAllTournamentByUserName(String currentUserName){
    	return tournamentRepository.getAllTournamentByUserName(currentUserName);
    }
}
