package com.softserveinc.edu.boardgames.service;

import java.util.List;

import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserGamesOfGameDTO;

public interface GameUserService {
	
	public GameUser getUserGamesById(Integer id);
	
	public GameUserDTO getUserGamesDTOById(Integer id);
	
	public List<GameUserDTO> getAllUsersGame();
	
	public List<GameUserDTO> getGameUsersByName(String name);
	
	public List<GameUser> getAllUserGames();
	
	public Integer getCountOfTournamentByGame(Integer id);
	
	public void update(GameUser gameUser);
	
	public void add(GameUser gameUser);
	
	public void deleteById(Integer id);
	
	public void delete(Integer id);
	
	public List<GameUserDTO> getGameUsersFromUsername(String username);
	
	public List<GameUserDTO> getMyGameUsersFromUsername(String username);
	
	public List<GameUserDTO> getSharedGameUsersFromUsername(String username);
	
	public List<UserGamesOfGameDTO> getAllUserGamesOfGame(String name);
	
	public List<Integer> getFromNameAndEdition(String name, String edition);

}
