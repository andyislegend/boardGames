package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.GameRating;

public interface GameRatingService {

	GameRating findById(Integer id);
	
	List<GameRating> getAll();
	
	@Transactional
	@Modifying
	void deleteCustom(Integer gameId, Integer userId);
	
	@Transactional
	void update(GameRating gameRatingNumeric);
	
	@Transactional
	void delete(GameRating gameRating);
	
	Integer getRatingforUser(Integer gameId, Integer userId);
	
	Double getForGameAndUser(Integer gameId, Integer userId);
	
	Double getAverageRating(Integer gameId);
	
	boolean checkIfUserRated(Integer gameId, Integer userId);
}
