package com.softserveinc.edu.boardgames.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllGamesDto;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameDetailsDTO;
import com.softserveinc.edu.boardgames.persistence.repository.GameRepository;

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
		return gameRepo.getAllGames();
	}
	
	public GameDetailsDTO getGamesById(Integer id){
		
		return gameRepo.getGameDescription(id);
	}
}
