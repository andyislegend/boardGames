package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.repository.GameRepository;

@Service
@Transactional
public class GameService {

	@Autowired
	private GameRepository gameRepo;

	public Game findById(Long id) {
		return gameRepo.findOne(id);
	}

	public List<Game> getAll() {
		return gameRepo.findAll();
	}

	@Transactional
	public void update(Game Game) {
		gameRepo.saveAndFlush(Game);
	}

	@Transactional
	public void create(Game Game) {
		gameRepo.save(Game);
	}
}
