package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.repository.GameUserRepository;

@Service
@Transactional
public class GameUserService {
	
	@Autowired
	private GameUserRepository gameUserRepo;
	
	public GameUser getUserGamesById(Long id){
		return gameUserRepo.findOne(id);
	}
	
	public List<GameUser> getAllUserGames(){
		return gameUserRepo.findAll();
	}
	
	@Transactional
	public void update(GameUser gameUser) {
		gameUserRepo.saveAndFlush(gameUser);
	}

	@Transactional
	public void create(GameUser gameUser) {
		gameUserRepo.save(gameUser);
	}
	
	public List<GameUser> getGameUsersFromUsername(String name) {
		return gameUserRepo.getAllGamesForCurrentUser(name);
	}
	
	public List<GameUser> getAllUserGamesOfGame(String name) {
		return gameUserRepo.getAllUserGamesOfGame(name);
	}
}
