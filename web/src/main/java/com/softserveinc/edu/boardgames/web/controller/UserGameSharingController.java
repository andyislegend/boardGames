package com.softserveinc.edu.boardgames.web.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softserveinc.edu.boardgames.persistence.entity.CommentsForGame;
import com.softserveinc.edu.boardgames.persistence.entity.Exchange;
import com.softserveinc.edu.boardgames.persistence.entity.GameProposition;
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.Notification;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.InfoFromApplierDTO;
import com.softserveinc.edu.boardgames.persistence.enumeration.GameUserStatus;
import com.softserveinc.edu.boardgames.persistence.enumeration.TimeEnum;
import com.softserveinc.edu.boardgames.service.CommentForGameService;
import com.softserveinc.edu.boardgames.service.ExchangeService;
import com.softserveinc.edu.boardgames.service.GamePropositionService;
import com.softserveinc.edu.boardgames.service.GameUserService;
import com.softserveinc.edu.boardgames.service.NotificationService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;
/**
 * @author Taras Varvariuk
 * Large controller performing operations 
 * with sharing and exchanging games
 * calucalating appropriate dates
 * creating and editing exchanges 
 */
@RestController
public class UserGameSharingController {
	
	final int NEUTRAL_ID = 0;
	
	final String DEFAULT_MESSAGE = "No message";
	
	final String NOTIFICATION_TYPE = "exchange";
	
	final String DEFAULT_COMMENT = "no comment";
	
	@Autowired
	private GameUserService gameUserService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExchangeService exchangeService;
	
	@Autowired
	private GamePropositionService gamePropoService;
	
	@Autowired
	private NotificationService notifyService;
	
	@Autowired
	private CommentForGameService commentService;
	
	public Notification processNotification(String message, User forWhoom, 
			User fromWhoom, GameUser gameUser) {
		
		Notification notification = new Notification();
		notification.setType(NOTIFICATION_TYPE);
		notification.setMessage(message);
		notification.setUserSender(fromWhoom);
		notification.setUser(forWhoom);
		notification.setGameUser(gameUser);
		return notification;
	}
	
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
			@PathVariable Integer returnDate) {
		
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus(GameUserStatus.AVAILABLE.name());
		gameUserService.update(gameUserToUpdate);
					
		Exchange exchange = new Exchange(NEUTRAL_ID, returnDate, DEFAULT_MESSAGE, 
				userService.getUser(WebUtil.getPrincipalUsername()), gameUserToUpdate);
		exchangeService.update(exchange);			
		
	}
	
	@RequestMapping(value="/makeGameUserPrivate/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public void makeGameUserPrivate(@PathVariable Integer gameUserId) {
		
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus(GameUserStatus.PRIVATE.name());
		gameUserService.update(gameUserToUpdate);
		
		exchangeService.delete(exchangeService.getByGameUserId(gameUserId));
	}
	
	@RequestMapping(value="/askGameUserOwnerToShare/{gameUserId}/{message}/{propositions}", method = RequestMethod.PUT)
	@ResponseBody
	public void askGameUserOwnerToShare(@PathVariable Integer gameUserId, @PathVariable String message,
			@PathVariable List<Integer> propositions) {
		
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus(GameUserStatus.CONFIRMATION.name());
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
		
		String messageForNotification = userService.findById(exchange.getUserApplierId()).getUsername() 
				+ " applies for the game " + gameUserToUpdate.getGame().getName() 
				+ ". Go to game manue and give your answer.";
		User userToNotify = userService.findById(exchange.getUser().getId());
		User userInvoker = userService.findById(exchange.getUserApplierId());
		notifyService.update(this.processNotification(messageForNotification, userToNotify, userInvoker, gameUserToUpdate));
	}
	
	@RequestMapping(value="/askGameUserOwnerToShare/{gameUserId}/{message}", method = RequestMethod.PUT)
	@ResponseBody
	public void askGameUserOwnerToShare(@PathVariable Integer gameUserId, @PathVariable String message) {
		
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus(GameUserStatus.CONFIRMATION.name());
		gameUserService.update(gameUserToUpdate);
		
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		exchange.setUserApplierId(userService.getUser(WebUtil.getPrincipalUsername()).getId());
		exchange.setMessage(message);
		exchangeService.update(exchange);
		
		String messageForNotification = userService.findById(exchange.getUserApplierId()).getUsername() 
				+ " applies for the game " + gameUserToUpdate.getGame().getName() 
				+ ". Go to game manue and give your answer.";
		User userToNotify = userService.findById(exchange.getUser().getId());
		User userInvoker = userService.findById(exchange.getUserApplierId());
		notifyService.update(this.processNotification(messageForNotification, userToNotify, userInvoker, gameUserToUpdate));
	}
	
	@RequestMapping(value="/acceptGameConfirmationRequest/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public void acceptGameConfirmation(@PathVariable Integer gameUserId) {
		
		GameUser gameUserOfOwner = gameUserService.getUserGamesById(gameUserId);
		gameUserOfOwner.setStatus(GameUserStatus.SHARED.name());
		gameUserService.update(gameUserOfOwner);
		
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		Date localDate = new Date();
		exchange.setApplyingDate(localDate);
		exchangeService.update(exchange);
		
		gamePropoService.updateForExchange(exchange.getId(), GameUserStatus.SHARED.name());
		
		String messageForNotification = WebUtil.getPrincipalUsername() 
		+ " confirmed your request for game " + gameUserOfOwner.getGame().getName() 
		+ ". Please contact the owner about further details";
		User userToNotify = userService.findById(exchange.getUserApplierId());
		User userInvoker = userService.findById(exchange.getUser().getId());
		notifyService.update(this.processNotification(messageForNotification, userToNotify, userInvoker, gameUserOfOwner));
	}
	
	@RequestMapping(value="/declineGameConfirmationRequest/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public void declineGameConfirmation(@PathVariable Integer gameUserId) {
		
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus(GameUserStatus.AVAILABLE.name());
		gameUserService.update(gameUserToUpdate);
		
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		exchange.setUserApplierId(NEUTRAL_ID);
		exchange.setMessage(DEFAULT_MESSAGE);
		exchangeService.update(exchange);
		
		gamePropoService.deleteForExchange(exchange.getId());
		
		String messageForNotification = "Unfortunately " + WebUtil.getPrincipalUsername() 
				+ " declined your request for game " 
				+ gameUserToUpdate.getGame().getName();
		User userToNotify = userService.findById(exchange.getUserApplierId());
		User userInvoker = userService.findById(exchange.getUser().getId());
		notifyService.update(
				this.processNotification(messageForNotification, userToNotify, userInvoker, gameUserToUpdate));
	}
	
	@RequestMapping(value="/giveGameBack/{gameUserId}/{comment}", method = RequestMethod.PUT)
	@ResponseBody
	public void giveGameBack(@PathVariable Integer gameUserId, @PathVariable String comment) {
		
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus(GameUserStatus.PRIVATE.name());
		gameUserService.update(gameUserToUpdate);
		
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		User userToNotify = userService.findById(exchange.getUser().getId());
		User userInvoker = userService.findById(exchange.getUserApplierId());
		
		gamePropoService.updateForExchange(exchange.getId(), GameUserStatus.PRIVATE.name());
		gamePropoService.deleteForExchange(exchange.getId());
		
		if (comment != this.DEFAULT_COMMENT) {
			CommentsForGame commentToSend = new CommentsForGame(
					gameUserToUpdate,userInvoker,comment,new Date());
			commentService.addComment(commentToSend);
		}
		
		String messageForNotification = userService.findById(exchange.getUserApplierId()).getUsername() 
				+ " intends to give " + gameUserToUpdate.getGame().getName() 
				+ " back. Contact " + WebUtil.getPrincipalUsername() + " about giving the game back.";
		notifyService.update(
				this.processNotification(messageForNotification, userToNotify, userInvoker, gameUserToUpdate));
				
		exchangeService.delete(exchange);
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
		Long days = (deadLine.getTime() - localDate.getTime())/ 
				(TimeEnum.HOURS.getValue() * TimeEnum.MINUTES.getValue() 
						* TimeEnum.SECONDS.getValue() * TimeEnum.MILISECONDS.getValue());
		return days.intValue();
	}
	
	@RequestMapping(value="/getPropositionsOfExchange/{gameUserId}", method = RequestMethod.GET)
	@ResponseBody
	public List<GameUserDTO> getPropositionsOfExchange(@PathVariable Integer gameUserId) {
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		return gamePropoService.getFromExchangeId(exchange.getId());
	}
}
