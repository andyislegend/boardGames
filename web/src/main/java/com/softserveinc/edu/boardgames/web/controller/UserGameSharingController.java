package com.softserveinc.edu.boardgames.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.Exchange;
import com.softserveinc.edu.boardgames.persistence.entity.GameProposition;
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.InfoFromApplierDTO;
import com.softserveinc.edu.boardgames.service.ExchangeService;
import com.softserveinc.edu.boardgames.service.GamePropositionService;
import com.softserveinc.edu.boardgames.service.GameUserService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

@RestController
public class UserGameSharingController {
	
	@Autowired
	private GameUserService gameUserService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExchangeService exchangeService;
	
	@Autowired
	private GamePropositionService gamePropoService;
	
	@RequestMapping(value="/checkIfGameBelongsToUser/{gameUserId}", method = RequestMethod.GET)
	@ResponseBody
	public boolean chechIfGameOwnerIsLoggedUser(@PathVariable Integer gameUserId) {
		User currentUser = userService.getUser(WebUtil.getPrincipalUsername());
		GameUser gamesGameUser = gameUserService.getUserGamesById(gameUserId);
		return currentUser.getId().equals(gamesGameUser.getUser().getId());
	}
	
	@RequestMapping(value="/checkIfGameIsBorrowed/{gameUserId}", method = RequestMethod.GET)
	@ResponseBody
	public boolean chechIfGameIsBorrowed(@PathVariable Integer gameUserId) {
		User user = userService.getUser(WebUtil.getPrincipalUsername());
		return exchangeService.checkIfBorrowed(user.getId(), gameUserId);
	}
	
	@RequestMapping(value="/getApplierUsername/{gameUserId}", method = RequestMethod.GET)
	@ResponseBody
	public InfoFromApplierDTO getUserToDisplayOnRequest(@PathVariable Integer gameUserId) {
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		return exchangeService.getExchangeDTO(exchange.getId());
	}
	
	@RequestMapping(value="/makeGameUserAvailable/{gameUserId}/{returnDate}", method = RequestMethod.PUT)
	@ResponseBody
	public void makeGameUserAvailable(@PathVariable Integer gameUserId, 
			@PathVariable Date returnDate) {
	
		Date localDate = new Date();
		if (returnDate.compareTo(localDate) > 0){
		
			GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
			gameUserToUpdate.setStatus("available");
			gameUserService.update(gameUserToUpdate);
					
			Exchange exchange = new Exchange();
			exchange.setGameUser(gameUserToUpdate);
			exchange.setMessage("Hey man!");
			Long days = (returnDate.getTime() - localDate.getTime())/ (24 * 60 * 60 * 1000);
			exchange.setPeriod(days.intValue());
			exchange.setUser(userService.getUser(WebUtil.getPrincipalUsername()));
			exchange.setUserApplierId(0);
			exchangeService.update(exchange);			
		}
	}
	
	@RequestMapping(value="/makeGameUserPrivate/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public void makeGameUserPrivate(@PathVariable Integer gameUserId) {
		
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus("private");
		gameUserService.update(gameUserToUpdate);
		
		exchangeService.delete(exchangeService.getByGameUserId(gameUserId));
	}
	
	@RequestMapping(value="/askGameUserOwnerToShare/{gameUserId}/{message}/{propositions}", method = RequestMethod.PUT)
	@ResponseBody
	public void askGameUserOwnerToShare(@PathVariable Integer gameUserId, @PathVariable String message,
			@PathVariable List<Integer> propositions) {
		
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus("confirmation");
		gameUserService.update(gameUserToUpdate);
		
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		exchange.setUserApplierId(userService.getUser(WebUtil.getPrincipalUsername()).getId());
		exchange.setMessage(message);
		exchangeService.update(exchange);
		
		for (Integer proposition: propositions) {
			GameProposition gamePropo = new GameProposition();
			gamePropo.setGameUser(gameUserService.getUserGamesById(proposition));
			gamePropo.setExchange(exchange);
			gamePropoService.update(gamePropo);
		}
	}
	
	@RequestMapping(value="/acceptGameConfirmationRequest/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public void acceptGameConfirmation(@PathVariable Integer gameUserId) {
		
		GameUser gameUserOfOwner = gameUserService.getUserGamesById(gameUserId);
		gameUserOfOwner.setStatus("shared");
		gameUserService.update(gameUserOfOwner);
		
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		Date localDate = new Date();
		exchange.setApplyingDate(localDate);
		exchangeService.update(exchange);
		
		for (GameUserDTO propo: gamePropoService.getFromExchangeId(exchange.getId())) {
			GameUser gameUser = gameUserService.getUserGamesById(propo.getId());
			gameUser.setStatus("shared");
			gameUserService.update(gameUser);
		}
	}
	
	@RequestMapping(value="/declineGameConfirmationRequest/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public void declineGameConfirmation(@PathVariable Integer gameUserId) {
		
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus("available");
		gameUserService.update(gameUserToUpdate);
		
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		exchange.setUserApplierId(0);
		exchange.setMessage("Hey you!");
		exchangeService.update(exchange);
		
		gamePropoService.deleteForExchange(exchange.getId());
	}
	
	@RequestMapping(value="/giveGameBack/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public void giveGameBack(@PathVariable Integer gameUserId) {
		
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus("private");
		gameUserService.update(gameUserToUpdate);
		
		exchangeService.delete(exchangeService.getByGameUserId(gameUserId));
	}
	
	@RequestMapping(value="/getHowManyDaysForExchange/{gameUserId}", method = RequestMethod.GET)
	@ResponseBody
	public Integer getHowManyDaysOwnerRequire(@PathVariable Integer gameUserId) {
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		return exchange.getPeriod();
	}
	
	@RequestMapping(value="/getHowManyDaysRemains/{gameUserId}", method = RequestMethod.GET)
	@ResponseBody
	public Integer getHowManyDaysRemains(@PathVariable Integer gameUserId) {
		
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		Date localDate = new Date();
		
		Date deadLine = exchange.getApplyingDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(deadLine);
		calendar.add(Calendar.DATE, exchange.getPeriod()); 
		deadLine = calendar.getTime();
		Long days = (deadLine.getTime() - localDate.getTime())/ (24 * 60 * 60 * 1000);
		return days.intValue();
	}
	
	@RequestMapping(value="/getPropositionsOfExchange/{gameUserId}", method = RequestMethod.GET)
	@ResponseBody
	public List<GameUserDTO> getPropositionsOfExchange(@PathVariable Integer gameUserId) {
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		return gamePropoService.getFromExchangeId(exchange.getId());
	}
}
