package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllGamesDto;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GamesChartDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UsersAgeChartDTO;

public interface GameService {

	Game findById(Integer id);
	
	List<Game> getAll();
	
	@Transactional
	void update(Game game);
	
	@Transactional
	void delete(Game game);
	
	List<AllGamesDto> getGamesDTO();
	
	Game findByName(String name);
	
	List<GamesChartDTO> groupGameUserByGame();
	
	List<GamesChartDTO> getRatingsForGame();
	
	List<UsersAgeChartDTO> countOfUsersOfAge();
}
