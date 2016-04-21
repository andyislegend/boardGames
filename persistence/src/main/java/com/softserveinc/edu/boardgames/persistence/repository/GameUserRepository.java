package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;

@Repository
public interface GameUserRepository extends JpaRepository<GameUser, Long> {
	
	@Query("select u.id as id, u.game.name as name, u.game.category.name as category,"
			+ " u.yearOfProduction as yearOfProduction, u.edition as edition, u.game.description as description,"
			+ " u.game.rules as rules, u.game.maxPlayers as maxPlayers, u.game.minPlayers as minPlayers "
			+ "from GameUser u ")
	public List<GameUserDTO> getAllGamesForCurrentUser();
	
	@Query("select u from GameUser u where u.user.username = :username ")
	public List<GameUser> getAllGameUserByUsername(@Param("username") String username);
	
	@Query("select u from GameUser u where u.game.name = :name")
	public List<GameUser> getAllUserGamesOfGame(@Param("name")String name);
}
