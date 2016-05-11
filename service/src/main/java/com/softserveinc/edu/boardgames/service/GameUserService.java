package com.softserveinc.edu.boardgames.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserGamesOfGameDTO;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.GameUserMapper;
import com.softserveinc.edu.boardgames.persistence.repository.CommentsForGameRepository;
import com.softserveinc.edu.boardgames.persistence.repository.GameUserRepository;

@Service
@Transactional
public class GameUserService {

	@Autowired
	private GameUserRepository gameUserRepo;
	
	@Autowired
	private CommentsForGameRepository commentsForGameRepository;

	public GameUser getUserGamesById(Integer id) {
		return gameUserRepo.getGameUserById(id);
	}
	
	public GameUserDTO getUserGamesDTOById(Integer id) {
		return gameUserRepo.getGameUserDTOById(id);
	}
	
	public List<GameUserDTO> getGameUsersByName(String name){
		return gameUserRepo.getGameUserByName(name);
	}

	public List<GameUser> getAllUserGames() {
		return gameUserRepo.findAll();
	}
	
	@Transactional
	public void update(GameUser gameUser) {
		gameUserRepo.saveAndFlush(gameUser);
	}

	@Transactional
	public void add(GameUser gameUser) {
		gameUserRepo.save(gameUser);
	}
	@Transactional
	public void deleteById(Integer id) {
		commentsForGameRepository.deleteByGameUser(id);
		gameUserRepo.deleteById(id);
	}
	
	@Transactional
	public void delete(Integer id) {
		gameUserRepo.delete(id);
	}

	public List<GameUserDTO> getGameUsersFromUsername(String username) {
		return gameUserRepo.getAllGameUserByUsername(username);
	}

	public List<GameUserDTO> getAllGameUserDTOs(List<GameUser> gameUsers) {
		List<GameUserDTO> gameUserDTOs = new ArrayList<>();
		GameUserMapper gameUserMapper = new GameUserMapper();
		for (GameUser gameUser : gameUsers) {
			gameUserDTOs.add(gameUserMapper.toDTO(gameUser));
		}
		return gameUserDTOs;
	}
	
	public List<UserGamesOfGameDTO> getAllUserGamesOfGame(String name) {
		return gameUserRepo.getUserGameOfGame(name);
	}
	
	public String getApplierUsernameFromGameId(Integer id) {
		return gameUserRepo.findApplierUsernameFromGameId(id);
	}
}
