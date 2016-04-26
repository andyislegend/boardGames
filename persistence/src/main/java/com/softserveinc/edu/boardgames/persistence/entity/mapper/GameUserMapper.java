package com.softserveinc.edu.boardgames.persistence.entity.mapper;

import com.softserveinc.edu.boardgames.persistence.entity.Category;
import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;

public class GameUserMapper implements GenericMapper<GameUserDTO, GameUser> {

	@Override
	public GameUserDTO toDTO(GameUser gameUser) {
		GameUserDTO gameUserDTO = new GameUserDTO();
		gameUserDTO.setId(gameUser.getId());
		gameUserDTO.setName(gameUser.getGame().getName());
		gameUserDTO.setCategory(gameUser.getGame().getCategory().getName());
		gameUserDTO.setYearOfProduction(gameUser.getYearOfProduction());
		gameUserDTO.setEdition(gameUser.getEdition());
		gameUserDTO.setDescription(gameUser.getGame().getDescription());
		gameUserDTO.setRules(gameUser.getGame().getRules());
		gameUserDTO.setMaxPlayers(gameUser.getGame().getMaxPlayers());
		gameUserDTO.setMinPlayers(gameUser.getGame().getMinPlayers());
		return gameUserDTO;
	}
	
	@Override
	public GameUser toEntity(GameUserDTO gameUserDTO) {
		GameUser gameUser = new GameUser();
		gameUser.setEdition(gameUserDTO.getEdition());
		gameUser.setYearOfProduction(gameUserDTO.getYearOfProduction());
		Category category = new Category(gameUserDTO.getCategory());
		gameUser.setGame(new Game(gameUserDTO.getName(), gameUserDTO.getDescription(), gameUserDTO.getMinPlayers(), gameUserDTO.getMaxPlayers(), category));
		return gameUser;
	}

}
