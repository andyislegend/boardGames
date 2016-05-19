package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.GameProposition;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.repository.GamePropositionRepository;

@Service
@Transactional
public class GamePropositionService {

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
	
	public void delete(GameProposition gameProposition) {
		gamePropositionRepo.delete(gameProposition);
	}
	
	public List<GameUserDTO> getFromExchangeId(Integer id) {
		return gamePropositionRepo.getAllForExchange(id);
	}
}
