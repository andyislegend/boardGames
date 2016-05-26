package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserGamesOfGameDTO;
import com.softserveinc.edu.boardgames.persistence.repository.CommentsForGameRepository;
import com.softserveinc.edu.boardgames.persistence.repository.GameUserRepository;

/**
 * @author Taras Varvariuk, Volodymyr Krokhmaliuk
 */
@Service
@Transactional
public class GameUserService {

	@Autowired
	private GameUserRepository gameUserRepo;
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private CommentsForGameRepository commentsForGameRepository;

	public GameUser getUserGamesById(Integer id) {
		return gameUserRepo.getGameUserById(id);
	}
	
	public GameUserDTO getUserGamesDTOById(Integer id) {
		return gameUserRepo.getGameUserDTOById(id);
	}
	
	public List<GameUserDTO> getAllUsersGame(){
		return gameUserRepo.getAllGameUsers();
	}
	
	public List<GameUserDTO> getGameUsersByName(String name){
		return gameUserRepo.getGameUserByName(name);
	}

	public List<GameUser> getAllUserGames() {
		return gameUserRepo.findAll();
	}
	
	public Integer getCountOfTournamentByGame(Integer id) {
		return gameUserRepo.getCountTournamentsByGame(id);
	}
	
	@Transactional
	public void update(GameUser gameUser) {
		gameUserRepo.saveAndFlush(gameUser);
	}

	@Transactional
	public void add(GameUser gameUser) {
		Game game = null;
	
		game = gameService.findByName(gameUser.getGame().getName());
	
		if(game == null) {
			gameUserRepo.save(gameUser);
		}else {
			gameUser.setGame(game);
			gameUserRepo.save(gameUser);
		}
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
	
	public List<GameUserDTO> getMyGameUsersFromUsername(String username) {
		return gameUserRepo.getAllMyGameUserByUsername(username);
	}
	
	public List<GameUserDTO> getSharedGameUsersFromUsername(String username) {
		return gameUserRepo.getAllSharedGameUserByUsername(username);
	}
	
	public List<UserGamesOfGameDTO> getAllUserGamesOfGame(String name) {
		return gameUserRepo.getUserGameOfGame(name);
	}
	
	public List<Integer> getFromNameAndEdition(String name, String edition) {
		return gameUserRepo.getGameUserFromNameAndEdition(name, edition);
	}
}
