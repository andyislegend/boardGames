package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.GameProposition;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.InfoFromApplierDTO;

@Repository
public interface GamePropositionRepository extends JpaRepository<GameProposition, Integer>{

	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO("
			+ "gu.id, gu.game.name, gu.edition, gu.yearOfProduction) "
			+ "from GameProposition gp "
			+ "inner join gp.exchange ex "
			+ "inner join gp.gameUser gu "
			+ "where ex.id = :exchangeId")
	public List<GameUserDTO> getAllForExchange(@Param("exchangeId")Integer id);
	
	@Modifying
	@Query("delete from GameProposition gp where gp.exchange.id = :exchangeId")
	public void deleteForExchange(@Param("exchangeId")Integer id);
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.InfoFromApplierDTO(gp.gameUser.id, "
			+ "ex.user.username, ex.message , gp.gameUser.game.name, gp.gameUser.game.category.name) "
			+ "from GameProposition gp "
			+ "inner join gp.exchange ex "
			+ "where ex.user.id = :userId")
	public List<InfoFromApplierDTO> getBorrowedGamesFromPropositions(@Param("userId")Integer userId);
}
