package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserGamesOfGameDTO;

@Repository
public interface GameUserRepository extends JpaRepository<GameUser, Integer> {
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO(u.id, u.game.name, u.game.category.name, u.yearOfProduction,"
			+ " u.edition, u.game.description, u.game.rules, u.game.maxPlayers,  u.game.minPlayers) from GameUser u where u.user.username = :username")   
	public List<GameUserDTO> getAllGameUserByUsername(@Param("username") String username);
		
	@Query("select u from GameUser u where u.id = :id")
	public GameUser getGameUserById(@Param("id") Integer id);
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.UserGamesOfGameDTO"
			+"(gu.id, gu.user.username, gu.edition, gu.yearOfProduction) " +
	       "from GameUser gu where gu.game.name = :name")
	List<UserGamesOfGameDTO> getUserGameOfGame(@Param("name")String name);
}