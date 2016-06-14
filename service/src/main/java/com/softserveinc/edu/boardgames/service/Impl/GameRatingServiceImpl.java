package com.softserveinc.edu.boardgames.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.GameRating;
import com.softserveinc.edu.boardgames.persistence.repository.GameRatingRepository;
import com.softserveinc.edu.boardgames.service.GameRatingService;

/**
 * @author Taras Varvariuk
 */
@Service
@Transactional
public class GameRatingServiceImpl implements GameRatingService {
	
	@Autowired
	private GameRatingRepository gameRatingNumericRepo;

	public GameRating findById(Integer id) {
		return gameRatingNumericRepo.findOne(id);
	}

	public List<GameRating> getAll() {
		return gameRatingNumericRepo.findAll();
	}

	@Transactional
	@Modifying
	public void deleteCustom(Integer gameId, Integer userId) {
		gameRatingNumericRepo.deleteCustom(gameId, userId);
	}
	
	@Transactional
	public void update(GameRating gameRatingNumeric) {
		gameRatingNumericRepo.saveAndFlush(gameRatingNumeric);
	}
	
	@Transactional
	public void delete(GameRating gameRating) {
		gameRatingNumericRepo.delete(gameRating);
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
	
	public void checkIfUserRated(Integer gameId, Integer userId) {
		if (gameRatingNumericRepo.checkIfUserRated(gameId, userId).size() >= 1) {
			this.deleteCustom(gameId, userId);
		}
	}
}
