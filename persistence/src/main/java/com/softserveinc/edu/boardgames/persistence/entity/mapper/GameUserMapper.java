package com.softserveinc.edu.boardgames.persistence.entity.mapper;

import com.softserveinc.edu.boardgames.persistence.entity.Category;
import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;

public class GameUserMapper  {

	
	public static GameUserDTO toDTO(GameUser gameUser) {
		GameUserDTO gameUserDTO = new GameUserDTO();
		gameUserDTO.setId(gameUser.getId());
		gameUserDTO.setName(gameUser.getGame().getName());
		gameUserDTO.setCategory(gameUser.getGame().getCategory().getName());
		gameUserDTO.setYearOfProduction(gameUser.getYearOfProduction());
		gameUserDTO.setEdition(gameUser.getEdition());
		gameUserDTO.setDescription(gameUser.getDescription());
		gameUserDTO.setRules(gameUser.getRules());
		gameUserDTO.setMaxPlayers(gameUser.getMaxPlayers());
		gameUserDTO.setMinPlayers(gameUser.getMinPlayers());
		gameUserDTO.setStatus(gameUser.getStatus());
		return gameUserDTO;
	}
	
	
	public static GameUser toEntity(GameUserDTO gameUserDTO) {
		GameUser gameUser = new GameUser();
		gameUser.setEdition(gameUserDTO.getEdition());
		gameUser.setYearOfProduction(gameUserDTO.getYearOfProduction());
		gameUser.setCountOfComments(0);
		gameUser.setStatus("PRIVATE");
		gameUser.setRules(gameUserDTO.getRules());
		gameUser.setDescription(gameUserDTO.getDescription());
		gameUser.setMinPlayers(gameUserDTO.getMinPlayers());
		gameUser.setMaxPlayers(gameUserDTO.getMaxPlayers());
		Category category = new Category(gameUserDTO.getCategory());
		gameUser.setGame(new Game(gameUserDTO.getName(),category));
		return gameUser;
	}

}
