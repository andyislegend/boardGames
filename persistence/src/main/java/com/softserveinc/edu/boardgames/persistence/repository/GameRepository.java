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
				+ "game.minPlayers, game.maxPlayers) " +
		       "from Game game")
	public List<AllGamesDto> getAllGames();
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.GameDetailsDTO"
			+"(game.name, game.description, game.rules, avg(gRateNum.rating)) "
			+ "from Game game, GameRatingNumeric gRateNum "
			+ "where game.id = :id "
			+ "and gRateNum.game.id = game.id")
	public GameDetailsDTO getGameDescription(@Param("id")Integer id);
	
	public Game findByName(String name);
}
