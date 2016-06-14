package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllGamesDto;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameDetailsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UsersAgeChartDTO;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>  {

	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.AllGamesDto"
				+ "(gu.game.id, gu.game.name, gu.game.category.name, count(gu), "
				+ "(select count(gu1) from GameUser gu1 "
					+ "where gu1.status = 'AVAILABLE' "
					+ "and gu1.game.id = gu.game.id)) "
				+ "from GameUser gu "
				+ "group by gu.game.name ")
	public List<AllGamesDto> getAllGames();
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.GameDetailsDTO"
			+ "(g.name, gr.rating, 0.0) "
			+ "from Game g, GameRating gr "
			+ "where g.id = :gameId and gr.game.id = g.id and gr.user.id = :userId")
	public GameDetailsDTO getGameDetails(@Param("gameId")Integer gameId, @Param("userId")Integer userId);
	
	public Game findByName(String name);
	
	@Query("select COUNT(gu) from GameUser gu "
			+ "where gu.game.id = :id")
	public Integer countGameUsersOfGame(@Param("id")Integer id);
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.UsersAgeChartDTO"
			+ "(gu.game.name, AVG(gu.user.age)) "
			+ "from GameUser gu "
			+ "group by gu.game.name")
	public List<UsersAgeChartDTO> countOfUsersOfAge();
}
