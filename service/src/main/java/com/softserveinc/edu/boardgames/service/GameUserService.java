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

@Service
@Transactional
public class GameUserService {

	@Autowired
	private GameUserRepository gameUserRepo;
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private CommentsForGameRepository commentsForGameRepository;

	/**
	 * This method get game by id
	 * 
	 * @param id
	 * @return game
	 */
	public GameUser getUserGamesById(Integer id) {
		return gameUserRepo.getGameUserById(id);
	}
	
	/**
	 * This method get game dto by id
	 * 
	 * @param id
	 * @return game
	 */
	public GameUserDTO getUserGamesDTOById(Integer id) {
		return gameUserRepo.getGameUserDTOById(id);
	}
	
	/**
	 * This method list of game 
	 * 
	 * @param id
	 * @return  list of games
	 */
	public List<GameUserDTO> getAllUsersGame(){
		return gameUserRepo.getAllGameUsers();
	}
	
	/**
	 * This method list of gamedto 
	 * 
	 * @param id
	 * @return  list of gamesdto
	 */
	public List<GameUserDTO> getGameUsersByName(String name){
		return gameUserRepo.getGameUserByName(name);
	}

	/**
	 * This method list of game 
	 * 
	 * @param id
	 * @return  list of games
	 */
	public List<GameUser> getAllUserGames() {
		return gameUserRepo.findAll();
	}
	
	/**
	 * This method get count of comment by game
	 * 
	 * @param id
	 * @return
	 */
	public Integer getCountOfTournamentByGame(Integer id) {
		return gameUserRepo.getCountTournamentsByGame(id);
	}
	
	/**
	 * This method update user
	 * 
	 * @param gameUser
	 */
	@Transactional
	public void update(GameUser gameUser) {
		gameUserRepo.saveAndFlush(gameUser);
	}

	/**
	 * This method add new game
	 * 
	 * @param gameUser
	 */
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
	
	/**
	 * This method delete game by id
	 * 
	 * @param id
	 */
	@Transactional
	public void deleteById(Integer id) {
		commentsForGameRepository.deleteByGameUser(id);
		gameUserRepo.deleteById(id);
	}
	
	/**
	 * This method delete game by id
	 * 
	 * @param id
	 */
	@Transactional
	public void delete(Integer id) {
		gameUserRepo.delete(id);
	}

	/**
	 * This method get game by username
	 * 
	 * @param username
	 * @return
	 */
	public List<GameUserDTO> getGameUsersFromUsername(String username) {
		return gameUserRepo.getAllGameUserByUsername(username);
	}
	
	/**
	 * This method get games by username
	 * 
	 * @param username
	 * @return
	 */
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
