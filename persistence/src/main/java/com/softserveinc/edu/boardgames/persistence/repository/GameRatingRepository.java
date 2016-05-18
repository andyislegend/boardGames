package com.softserveinc.edu.boardgames.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.GameRating;

@Repository
public interface GameRatingRepository extends JpaRepository<GameRating, Integer>{

	@Query("select gameRateNum.rating "
			+ "from GameRating gameRateNum "
			+ "where gameRateNum.game.id = :gameId "
			+ "and gameRateNum.user.id = :userId")
	Integer getGameRated(@Param("gameId")Integer gameId, @Param("userId")Integer userId);
	
	@Query("update GameRating grn "
			+ "set grn.rating = :rating "
			+ "where grn.game.id = :gameId "
			+ "and grn.user.id = :userId")
	@Modifying
	void updateRating(@Param("gameId")Integer gameId, 
			@Param("userId")Integer userId, @Param("rating")Integer rating);
	
	@Query("select grn from GameRating grn "
			+ "where grn.game.id = :gameId")
	GameRating getFromGame(@Param("gameId")Integer gameId);
}
