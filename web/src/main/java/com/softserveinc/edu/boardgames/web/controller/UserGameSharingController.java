package com.softserveinc.edu.boardgames.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.service.GameUserService;

@RestController
public class UserGameSharingController {
	
	@Autowired
	private GameUserService gameUserService;
	
	@RequestMapping(value="/sendGameBorrowRequest/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public void sendGameBorrowRequest(@PathVariable Integer gameUserId) {
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus("confirmation");
		gameUserService.update(gameUserToUpdate);
	}

}
