package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserGamesOfGameDTO;

@Repository
public interface GameUserRepository extends JpaRepository<GameUser, Integer> {
	
	@Modifying
	@Query("delete from GameUser g where g.id = :id")
	public void deleteById(@Param("id")Integer id);
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO("
			+ "u.id, u.game.name, "
			+ "u.game.category.name, u.yearOfProduction,"
			+ " u.edition, u.countOfComments, u.status, "
			+ "u.description, u.rules, "
			+ "u.maxPlayers, u.minPlayers) "
			+ "from GameUser u where u.game.name like %:name% ")
	public List<GameUserDTO> getGameUserByName(@Param("name")String name);
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO("
			+ "u.id, u.game.name, u.game.category.name, "
			+ "u.yearOfProduction, u.edition, u.countOfComments, u.status,"
			+ "u.description, u.rules, u.maxPlayers, "
			+ "u.minPlayers) "
			+ "from GameUser u where u.user.username = :username")   
	public List<GameUserDTO> getAllGameUserByUsername(@Param("username") String username);
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO("
			+ "u.id, u.game.name, u.game.category.name, "
			+ "u.yearOfProduction, u.edition, u.countOfComments, u.status,"
			+ "u.description, u.rules, u.maxPlayers, "
			+ "u.minPlayers) "
			+ "from GameUser u")   
	public List<GameUserDTO> getAllGameUsers();
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO("
			+ "u.id, u.game.name, u.game.category.name, "
			+ "u.yearOfProduction, u.edition, u.countOfComments, u.status,"
			+ "u.description, u.rules, u.maxPlayers, "
			+ "u.minPlayers) "
			+ "from GameUser u "
			+ "where u.user.username = :username "
			+ "and u.status != 'SHARED'")   
	public List<GameUserDTO> getAllMyGameUserByUsername(@Param("username") String username);
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO("
			+ "u.id, u.game.name, u.game.category.name, "
			+ "u.yearOfProduction, u.edition, u.countOfComments, u.status,"
			+ "u.description, u.rules, u.maxPlayers, "
			+ "u.minPlayers, user.username) "
			+ "from GameUser u, Exchange e, User user "
			+ "where u.user.username = :username "
			+ "and u.status = 'SHARED' "
			+ "and e.gameUser.id = u.id "
			+ "and user.id = e.userApplierId")   
	public List<GameUserDTO> getAllSharedGameUserByUsername(@Param("username") String username);
	
	@Query("select u from GameUser u where u.id = :id")
	public GameUser getGameUserById(@Param("id") Integer id);
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO("
			+ "u.id, u.game.name, "
			+ "u.game.category.name, u.yearOfProduction,"
			+ " u.edition, u.countOfComments, u.status, u.description, "
			+ "u.rules, u.maxPlayers, u.minPlayers) "
			+ "from GameUser u where u.id = :id")
	public GameUserDTO getGameUserDTOById(@Param("id") Integer id);
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.UserGamesOfGameDTO"
			+"(gu.id, gu.user.username, gu.edition, gu.yearOfProduction, gu.status) " +
	       "from GameUser gu where gu.game.name = :name")
	public List<UserGamesOfGameDTO> getUserGameOfGame(@Param("name")String name);
	
	@Query("select u.id from GameUser u where "
			+ "u.game.name = :name "
			+ "and u.edition = :edition")
	public List<Integer> getGameUserFromNameAndEdition(@Param("name")String name, @Param("edition")String edition);
}