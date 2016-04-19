package com.softserveinc.edu.boardgames.service.mapper;

import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.service.dto.GameUserDTO;

public class GameUserMapper implements GenericMapper<GameUserDTO, GameUser> {

	@Override
	public GameUserDTO toDTO(GameUser gameUser) {
		GameUserDTO gameUserDTO = new GameUserDTO();
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

}
