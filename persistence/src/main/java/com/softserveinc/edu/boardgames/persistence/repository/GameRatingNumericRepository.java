package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.GameRatingNumeric;

@Repository
public interface GameRatingNumericRepository extends JpaRepository<GameRatingNumeric, Integer>{

	@Query("select gameRateNum.rating "
			+ "from GameRatingNumeric gameRateNum "
			+ "where gameRateNum.game.id = :gameId "
			+ "and gameRateNum.user.id = :userId")
	List<Integer> getGameRated(@Param("gameId")Integer gameId, @Param("userId")Integer userId);
	
	@Query("update GameRatingNumeric grn "
			+ "set grn.rating = :rating "
			+ "where grn.game.id = :gameId "
			+ "and grn.user.id = :userId")
	@Modifying
	void updateRating(@Param("gameId")Integer gameId, 
			@Param("userId")Integer userId, @Param("rating")Integer rating);
	
	@Query("select grn from GameRatingNumeric grn "
			+ "where grn.game.id = :gameId "
			+ "and grn.user.id = :userId")
	List<GameRatingNumeric> getFromGameAndUser(@Param("gameId")Integer gameId, 
			@Param("userId")Integer userId);
}
