package com.softserveinc.edu.boardgames.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.GameProposition;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.InfoFromApplierDTO;
import com.softserveinc.edu.boardgames.persistence.repository.GamePropositionRepository;
import com.softserveinc.edu.boardgames.service.GamePropositionService;

/**
 * @author Taras Varvariuk
 */
@Service
@Transactional
public class GamePropositionServiceImpl implements GamePropositionService {

	@Autowired
	private GamePropositionRepository gamePropositionRepo;
	
	public GameProposition findById(Integer id) {
		return gamePropositionRepo.findOne(id);
	}
	
	public List<GameProposition> findAllGamePropositions() {
		return gamePropositionRepo.findAll();
	}
	
	@Transactional
	public void update(GameProposition gameProposition) {
		gamePropositionRepo.saveAndFlush(gameProposition);
	}
	
	@Transactional
	public void delete(GameProposition gameProposition) {
		gamePropositionRepo.delete(gameProposition);
	}
	
	public List<GameUserDTO> getFromExchangeId(Integer exchangeId) {
		return gamePropositionRepo.getAllForExchange(exchangeId);
	}
	
	@Modifying
	@Transactional
	public void deleteForExchange(Integer exchangeID) {
		gamePropositionRepo.deleteForExchange(exchangeID);
	}
	
	public List<InfoFromApplierDTO> getAllGamePropositionsForUser(Integer userId) {
		return gamePropositionRepo.getBorrowedGamesFromPropositions(userId);
	}
}
