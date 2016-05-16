package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
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
	@Modifying
	public void updateManualy(Integer gameId, Integer userId, Integer rating) {
		gameRatingNumericRepo.updateRating(gameId, userId, rating);
	}
	
	@Transactional
	public void update(GameRatingNumeric gameRatingNumeric) {
		gameRatingNumericRepo.save(gameRatingNumeric);
	}
	
	public Integer getRatingforUser(Integer gameId, Integer userId){
		Integer rating = gameRatingNumericRepo.getGameRated(gameId, userId);
		if (rating == null)
			rating = 0;
		return rating;
	}
	
	public GameRatingNumeric getFromGame(Integer gameId){
		return gameRatingNumericRepo.getFromGame(gameId);
	}
}
