package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.GameRatingNumeric;
import com.softserveinc.edu.boardgames.persistence.repository.GameRatingNumericRepository;

@Service
@Transactional
public class GameRatingNumericService {
	
	@Autowired
	private GameRatingNumericRepository gameRatingNumericRepo;

	public GameRatingNumeric findById(Integer id) {
		return gameRatingNumericRepo.findOne(id);
	}

	public List<GameRatingNumeric> getAll() {
		return gameRatingNumericRepo.findAll();
	}

	@Transactional
	public void update(GameRatingNumeric gameRateNum) {
		gameRatingNumericRepo.saveAndFlush(gameRateNum);
	}

	@Transactional
	public void create(GameRatingNumeric gameRateNum) {
		gameRatingNumericRepo.save(gameRateNum);
	}
	
	public Integer getRatingforUser(Integer gameId, String username){
		return gameRatingNumericRepo.getGameRated(gameId, username);
	}
}
