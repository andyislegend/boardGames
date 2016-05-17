package com.softserveinc.edu.boardgames.web.controller;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.Exchange;
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.InfoFromApplierDTO;
import com.softserveinc.edu.boardgames.service.ExchangeService;
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
			@PathVariable java.util.Date returnDate) {
	
		LocalDate localDate = LocalDate.now();
		LocalDate userDate = returnDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
		if (localDate.getYear() <= userDate.getYear()) {
			if (localDate.getDayOfYear() <= userDate.getDayOfYear()) {
					
				GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
				gameUserToUpdate.setStatus("available");
				gameUserService.update(gameUserToUpdate);
					
				Exchange exchange = new Exchange();
				exchange.setGameUser(gameUserToUpdate);
				exchange.setMessage("Hey man!");
				Long days = localDate.until( userDate, ChronoUnit.DAYS);
				exchange.setPeriod(days.intValue());
				exchange.setUser(userService.getUser(WebUtil.getPrincipalUsername()));
				exchange.setUserApplierId(0);
				exchangeService.update(exchange);
			}
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
	
	@RequestMapping(value="/askGameUserOwnerToShare/{gameUserId}/{message}", method = RequestMethod.PUT)
	@ResponseBody
	public void askGameUserOwnerToShare(@PathVariable Integer gameUserId, @PathVariable String message) {
		
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus("confirmation");
		gameUserService.update(gameUserToUpdate);
		
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		exchange.setUserApplierId(userService.getUser(WebUtil.getPrincipalUsername()).getId());
		exchange.setMessage(message);
		exchangeService.update(exchange);
	}
	
	@RequestMapping(value="/acceptGameConfirmationRequest/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public void acceptGameConfirmation(@PathVariable Integer gameUserId) {
		
		GameUser gameUserOfOwner = gameUserService.getUserGamesById(gameUserId);
		gameUserOfOwner.setStatus("shared");
		gameUserService.update(gameUserOfOwner);
		
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		LocalDate localDate = LocalDate.now();
		exchange.setApplyingDate(Date.from(localDate
				.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		exchangeService.update(exchange);
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
		LocalDate localDate = LocalDate.now();
		
		java.util.Date date = exchange.getApplyingDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, exchange.getPeriod()); 
		date = calendar.getTime();
		
		LocalDate deadLine = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return Period.between(localDate, deadLine).getDays();
	}
}
