package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.GameProposition;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;

@Repository
public interface GamePropositionRepository extends JpaRepository<GameProposition, Integer>{

	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO("
			+ "gu.id, gu.game.name, gu.edition, gu.yearOfProduction) "
			+ "from GameProposition gp "
			+ "inner join gp.exchange ex "
			+ "inner join gp.gameUser gu "
			+ "where ex.id = :exchangeId")
	public List<GameUserDTO> getAllForExchange(@Param("exchangeId")Integer id);
}
