package com.softserveinc.edu.boardgames.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.GameRatingNumeric;

@Repository
public interface GameRatingNumericRepository extends JpaRepository<GameRatingNumeric, Integer>{

	@Query("select gameRateNum.rating from GameRatingNumeric gameRateNum "
			+ "where gameRateNum.game.id = :gameId and gameRateNum.user.username = :username")
	Integer getGameRated(@Param("gameId")Integer gameId, @Param("username")String username);
}
