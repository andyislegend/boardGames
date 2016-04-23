package com.softserveinc.edu.boardgames.service;

import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.TournamentComposition;
import com.softserveinc.edu.boardgames.persistence.repository.TournamentCompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Daria Bondar
 * @since 12.04.2016
 */

@Service
public class TournamentCompositionService {

    @Autowired
    TournamentCompositionRepository tournamentCompositionRepository;

    @Transactional
    public void save(TournamentComposition tournamentComposition){
        tournamentCompositionRepository.save(tournamentComposition);
    }

    @Transactional
    public void update(TournamentComposition tournamentComposition){
        tournamentCompositionRepository.saveAndFlush(tournamentComposition);
    }

    @Transactional
    public void delete(TournamentComposition tournamentComposition){
        tournamentCompositionRepository.delete(tournamentComposition);
    }

    public List<TournamentComposition> findAll(){
        return tournamentCompositionRepository.findAll();
    }

    public TournamentComposition findById(Long id){
        return tournamentCompositionRepository.findOne(id);
    }

    public List<TournamentComposition> findByTournamentId(Long id){
        return tournamentCompositionRepository.findByTournamentId(id);
    }

    public Long findCountUserGuest(String username,Long idComposition){
        return tournamentCompositionRepository.findCountUserGuest(username,idComposition);
    }
    
    public List<TournamentComposition> findByUsersUsername(String username){
        return tournamentCompositionRepository.findUserByUsername(username);
    }
}
