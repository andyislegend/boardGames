package com.softserveinc.edu.boardgames.service;

import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO;
import com.softserveinc.edu.boardgames.persistence.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Volodymyr Krokhmalyuk
 * @since 12.04.2016
 */
@Service
public class TournamentService {

    @Autowired
    TournamentRepository tournamentRepository;

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
    	tournamentRepository.addParticipantToTournament(tournamentId, userId);
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
    
    /**
     *@author Vasyl Bervetskyy
     **/
    public List<Object[]> getAllTournamentByUserName(String currentUserName){
    	return tournamentRepository.getAllTournamentByUserName(currentUserName);
    }
}
