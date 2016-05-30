package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllGamesDto;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UsersAgeChartDTO;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>  {

	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.AllGamesDto"
				+"(game.id, game.name, game.category.name)from Game game ")
	public List<AllGamesDto> getAllGames();
	
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
