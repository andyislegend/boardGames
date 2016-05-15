package com.softserveinc.edu.boardgames.persistence.entity.mapper;

import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO;

/**
 * 
 * @author Krokhmalyuk
 *
 */
public class TournamentMapper {
	
	public static Tournament toEntity(TournamentsDTO dto) {
		Tournament tournament = new Tournament();
		tournament.setName(dto.getTournamentName());
		tournament.setCountOfParticipants(dto.getCountOfParticipants());
		tournament.setDateOfTournament(dto.getDate());
		tournament.setCountry(dto.getCountry());
		tournament.setCity(dto.getCity());
		
		return tournament;
	}
}
