package com.softserveinc.edu.boardgames.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.GameUserService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@RestController
public class UserGameSharingController {
	
	@Autowired
	private GameUserService gameUserService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/checkIfGameBelongsToUser/{gameUserId}", method = RequestMethod.GET)
	@ResponseBody
	public boolean chechIfGameOwnerIsLoggedUser(@PathVariable Integer gameUserId) {
		User currentUser = userService.getUser(WebUtil.getPrincipalUsername());
		GameUser gamesGameUser = gameUserService.getUserGamesById(gameUserId);
		return currentUser.getId().equals(gamesGameUser.getUser().getId());
	}
	
	@RequestMapping(value="/getApplierUsername/{gameUserId}", method = RequestMethod.GET)
	@ResponseBody
	public String getUserToDisplayOnRequest(@PathVariable Integer gameUserId) {
		return gameUserService.getApplierUsernameFromGameId(gameUserId);
	}
	
	@RequestMapping(value="/makeGameUserAvailable/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public void makeGameUserAvailable(@PathVariable Integer gameUserId) {
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus("available");
		gameUserService.update(gameUserToUpdate);
	}
	
	@RequestMapping(value="/makeGameUserPrivate/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public void makeGameUserPrivate(@PathVariable Integer gameUserId) {
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus("private");
		gameUserService.update(gameUserToUpdate);
	}
	
	@RequestMapping(value="/askGameUserOwnerToShare/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public void askGameUserOwnerToShare(@PathVariable Integer gameUserId) {
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus("confirmation");
		gameUserToUpdate.setUserApplierId(userService.getUser(WebUtil.getPrincipalUsername()).getId());
		gameUserService.update(gameUserToUpdate);
	}
	
	@RequestMapping(value="/acceptGameConfirmationRequest/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public void acceptGameConfirmation(@PathVariable Integer gameUserId) {
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setUserOwnerId(userService.getUser(WebUtil.getPrincipalUsername()).getId());
		gameUserToUpdate.setUser(userService.findById(gameUserToUpdate.getUserApplierId()));
		gameUserToUpdate.setUserApplierId(0);
		gameUserToUpdate.setStatus("borrowed");
		gameUserService.update(gameUserToUpdate);
	}
	
	@RequestMapping(value="/declineGameConfirmationRequest/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public void declineGameConfirmation(@PathVariable Integer gameUserId) {
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setUserApplierId(0);
		gameUserToUpdate.setStatus("available");
		gameUserService.update(gameUserToUpdate);
	}
	
	@RequestMapping(value="/giveGameBack/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public void giveGameBack(@PathVariable Integer gameUserId) {
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus("private");
		gameUserToUpdate.setUserApplierId(0);
		gameUserToUpdate.setUser(userService.findById(gameUserToUpdate.getUserOwnerId()));
		gameUserService.update(gameUserToUpdate);
	}
}
