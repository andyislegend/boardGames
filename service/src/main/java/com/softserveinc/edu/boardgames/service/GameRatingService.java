package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.GameRating;
import com.softserveinc.edu.boardgames.persistence.repository.GameRatingRepository;

@Service
@Transactional
public class GameRatingService {
	
	@Autowired
	private GameRatingRepository gameRatingNumericRepo;

	public GameRating findById(Integer id) {
		return gameRatingNumericRepo.findOne(id);
	}

	public List<GameRating> getAll() {
		return gameRatingNumericRepo.findAll();
	}

//	@Transactional
//	@Modifying
//	public void updateManualy(Integer gameId, Integer userId, Integer rating) {
//		gameRatingNumericRepo.updateRating(gameId, userId, rating);
//	}
	
	@Transactional
	public void update(GameRating gameRatingNumeric) {
		gameRatingNumericRepo.saveAndFlush(gameRatingNumeric);
	}
	
	public Integer getRatingforUser(Integer gameId, Integer userId){
		Integer rating = gameRatingNumericRepo.getGameRated(gameId, userId);
		if (rating == null)
			rating = 0;
		return rating;
	}
	
	public Double getForGameAndUser(Integer gameId, Integer userId){
		return gameRatingNumericRepo.getForGameAndUser(gameId, userId);
	}
	
	public Double getAverageRating(Integer gameId) {
		return gameRatingNumericRepo.getAverageRatingForGame(gameId);
	}
	
	public boolean checkIfUserRated(Integer gameId, Integer userId) {
		return (gameRatingNumericRepo.checkIfUserRated(gameId, userId).size() > 1);
	}
}
