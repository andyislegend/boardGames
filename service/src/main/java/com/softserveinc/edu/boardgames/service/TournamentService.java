package com.softserveinc.edu.boardgames.service;

import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllTournamentsDTO;
import com.softserveinc.edu.boardgames.persistence.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    public List<AllTournamentsDTO> getAllTornaments() {
    	return  tournamentRepository.getAllTournaments();
    }

    public List<Tournament> findAll(){
        return tournamentRepository.findAll();
    }

    public Tournament findById(Integer id){
        return tournamentRepository.findOne(id);
    }
}
