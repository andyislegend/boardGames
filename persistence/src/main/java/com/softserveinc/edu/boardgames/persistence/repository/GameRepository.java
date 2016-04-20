package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>  {

	@Query("select game from Game game where game.name = :name")
	Game findGameByName(@Param("name")String name);
}
