package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;

@Repository
public interface GameUserRepository extends JpaRepository<GameUser, Long> {
	@Query("select game from GameUser gameUser where gameUser.game.id = '1'")
	public List<Game> getAllGamesForCurrentUser();
}
