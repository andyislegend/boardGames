package com.softserveinc.edu.boardgames.service;

import java.util.Date;
import java.util.List;

import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO;

public interface TournamentService {
	
	public void save(Tournament tournament);
	
	public void update(Tournament tournament);
	
	public void delete(Tournament tournament);
	
	public void addParticipantToTournament(Integer tournamentId, Integer userId);
	
	public void deleteParticipantsFromTournamnet(Integer tournamentId, Integer userId);
	
	public void deleteTournament(Integer id);
	
	public List<TournamentsDTO> getAllTornaments();
	
	public Tournament getTournamenById(Integer id);
	
	public List<Tournament> findAll();
	
	public TournamentsDTO getTournamentById(Integer id);
	
	public List<TournamentsDTO> getTournamentsByWord(String name);
	
	public List<TournamentsDTO> getAllTournamentByUserCreator(String username);
	
	public List<Tournament> getAllTommorowTournament();
	
	public List<Date> getAllTournamentsDates();
	
	public Integer countTournamentsOnDate(Date date);

}
