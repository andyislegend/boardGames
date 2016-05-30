package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.GameProposition;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.InfoFromApplierDTO;

public interface GamePropositionService {

	GameProposition findById(Integer id);
	
	List<GameProposition> findAllGamePropositions();
	
	@Transactional
	void update(GameProposition gameProposition);

	@Transactional
	void delete(GameProposition gameProposition);

	List<GameUserDTO> getFromExchangeId(Integer exchangeId);
	
	@Modifying
	@Transactional
	void deleteForExchange(Integer exchangeID);
	
	List<InfoFromApplierDTO> getAllGamePropositionsForUser(Integer userId);
}
