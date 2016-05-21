package com.softserveinc.edu.boardgames.persistence.entity.mapper;

import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllTournamentsDTO;

/**
 * 
 * @author Krokhmalyuk
 *
 */
public class TournamentMapper {
	
	public static Tournament toEntity(AllTournamentsDTO dto) {
		Tournament tournament = new Tournament();
		tournament.setName(dto.getTournamentName());
		tournament.setCountOfParticipants(dto.getCountOfParticipants());
		tournament.setDateOfTournament(dto.getDateOfTournament());
		tournament.setCountry(dto.getCountry());
		tournament.setCity(dto.getCity());
		tournament.setCanRate(true);
		
		return tournament;
	}
}
