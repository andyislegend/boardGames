package com.softserveinc.edu.boardgames.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.repository.GameRepository;
import com.softserveinc.edu.boardgames.service.dto.AllGamesDto;
import com.softserveinc.edu.boardgames.service.dto.GameDetailsDTO;

@Service
@Transactional
public class GameService {

	@Autowired
	private GameRepository gameRepo;

	public Game findById(Integer id) {
		return gameRepo.findOne(id);
	}

	public List<Game> getAll() {
		return gameRepo.findAll();
	}

	@Transactional
	public void update(Game game) {
		gameRepo.saveAndFlush(game);
	}

	@Transactional
	public void create(Game game) {
		gameRepo.save(game);
	}
	
	public List<AllGamesDto> getGamesDTO(){
		List<AllGamesDto> gamesTransfer = new ArrayList<AllGamesDto>();
		for (Game game : this.getAll()){
			AllGamesDto gamesDto = new AllGamesDto();
			gamesDto.setId(game.getId());
			gamesDto.setName(game.getName());
			gamesDto.setDescription(game.getDescription());
			gamesDto.setMinPlayers(game.getMinPlayers());
			gamesDto.setMaxPlayers(game.getMaxPlayers());
			gamesDto.setCategoryName(game.getCategory().getName());
			gamesDto.setRating(game.getRating());
			gamesTransfer.add(gamesDto);
		}
		return gamesTransfer;
	}
	
	public GameDetailsDTO getGamesById(Integer id){
			
		GameDetailsDTO gameDetailsDTO = new GameDetailsDTO();
		gameDetailsDTO.setName(gameRepo.findOne(id).getName());
		gameDetailsDTO.setDescription(gameRepo.findOne(id).getDescription());
		gameDetailsDTO.setRules(gameRepo.findOne(id).getRules());
		gameDetailsDTO.setRating(gameRepo.findOne(id).getRating());
		
		return gameDetailsDTO;
	}
}
