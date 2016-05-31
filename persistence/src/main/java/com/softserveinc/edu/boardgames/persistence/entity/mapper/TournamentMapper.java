package com.softserveinc.edu.boardgames.persistence.entity.mapper;

import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentCreateDTO;

/**
 * 
 * @author Volodymyr Krokhmalyuk
 *
 */
public class TournamentMapper {
	
	public static Tournament toEntity(TournamentCreateDTO dto) {
		Tournament tournament = new Tournament();
		tournament.setName(dto.getName());
		tournament.setCountOfParticipants(dto.getCountOfParticipants());
		tournament.setDateOfTournament(dto.getDateOfTournament());
		tournament.setCountry(dto.getCountry());
		tournament.setCity(dto.getCity());
		
		return tournament;
	}
}
