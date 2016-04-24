package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllGamesDto;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameDetailsDTO;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>  {

	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.AllGamesDto"
				+"(game.id, game.name, game.category.name, "
				+ "game.minPlayers, game.maxPlayers, game.rating) " +
		       "from Game game")
	List<AllGamesDto> getAllGames();
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.GameDetailsDTO"
			+"(game.name, game.description, game.rules, game.rating) " +
	       "from Game game where game.id = :id")
	GameDetailsDTO getGameDescription(@Param("id")Integer id);
	
	@Query("select avg(gRateNum.rating) from GameRatingNumeric gRateNum where gRateNum.game.id = :id")
	Integer getAverageGameRating(@Param("id")Integer id);
}
