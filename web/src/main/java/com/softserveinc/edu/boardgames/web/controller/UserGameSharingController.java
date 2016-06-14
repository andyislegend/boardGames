package com.softserveinc.edu.boardgames.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.softserveinc.edu.boardgames.persistence.enumeration.GameUserStatus;
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
		
	private static final String DEFAULT_MESSAGE = "No message";
			
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
	
	@RequestMapping(value="/makeGameUserAvailable/{gameUserId}/{returnDate}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> makeGameUserAvailable(@PathVariable Integer gameUserId, 
			@PathVariable Integer returnDate) {
		
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus(GameUserStatus.AVAILABLE.name());
		gameUserService.update(gameUserToUpdate);
					
		Exchange exchange = new Exchange(returnDate, DEFAULT_MESSAGE, 
				userService.getUser(WebUtil.getPrincipalUsername()), gameUserToUpdate);
		exchangeService.update(exchange);			
		return new ResponseEntity<String>(exchange.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/makeGameUserPrivate/{gameUserId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> makeGameUserPrivate(@PathVariable Integer gameUserId) {
		
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus(GameUserStatus.PRIVATE.name());
		gameUserService.update(gameUserToUpdate);
		
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		exchangeService.delete(exchange);
		return new ResponseEntity<String>(exchange.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/askGameUserOwnerToShare/{gameUserId}/{message}/{propositions}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> askGameUserOwnerToShare(@PathVariable Integer gameUserId, @PathVariable String message,
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
		notifyService.processNotificationForExchange(messageForNotification, 
				userToNotify, userInvoker, gameUserToUpdate);
		return new ResponseEntity<String>(exchange.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/askGameUserOwnerToShare/{gameUserId}/{message}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> askGameUserOwnerToShare(@PathVariable Integer gameUserId, @PathVariable String message) {
		
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
		notifyService.processNotificationForExchange(messageForNotification, 
				userToNotify, userInvoker, gameUserToUpdate);		
		return new ResponseEntity<String>(exchange.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/acceptGameConfirmationRequest/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> acceptGameConfirmation(@PathVariable Integer gameUserId) {
		
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
		notifyService.processNotificationForExchange(messageForNotification, 
				userToNotify, userInvoker, gameUserOfOwner);
		return new ResponseEntity<String>(exchange.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/declineGameConfirmationRequest/{gameUserId}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> declineGameConfirmation(@PathVariable Integer gameUserId) {
		
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus(GameUserStatus.AVAILABLE.name());
		gameUserService.update(gameUserToUpdate);
		
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		exchange.setMessage(DEFAULT_MESSAGE);
		exchangeService.update(exchange);
		
		gamePropoService.deleteForExchange(exchange.getId());
		
		String messageForNotification = "Unfortunately " + WebUtil.getPrincipalUsername() 
				+ " declined your request for game " 
				+ gameUserToUpdate.getGame().getName();
		User userToNotify = userService.findById(exchange.getUserApplierId());
		User userInvoker = userService.findById(exchange.getUser().getId());
		notifyService.processNotificationForExchange(messageForNotification, 
				userToNotify, userInvoker, gameUserToUpdate);
		return new ResponseEntity<String>(exchange.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/giveGameBack/{gameUserId}/{comment}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> giveGameBack(@PathVariable Integer gameUserId, @PathVariable String comment) {
		
		GameUser gameUserToUpdate = gameUserService.getUserGamesById(gameUserId);
		gameUserToUpdate.setStatus(GameUserStatus.PRIVATE.name());
		gameUserService.update(gameUserToUpdate);
		
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		User userToNotify = userService.findById(exchange.getUser().getId());
		User userInvoker = userService.findById(exchange.getUserApplierId());
		
		gamePropoService.updateForExchange(exchange.getId(), GameUserStatus.PRIVATE.name());
		gamePropoService.deleteForExchange(exchange.getId());
		
		commentService.processCommentForExchange(gameUserToUpdate, userInvoker, comment);
		
		String messageForNotification = userService.findById(exchange.getUserApplierId()).getUsername() 
				+ " intends to give " + gameUserToUpdate.getGame().getName() 
				+ " back. Contact " + WebUtil.getPrincipalUsername() + " about giving the game back.";
		notifyService.processNotificationForExchange(messageForNotification, 
				userToNotify, userInvoker, gameUserToUpdate);
		exchangeService.delete(exchange);
		return new ResponseEntity<String>(exchange.toString(), HttpStatus.OK);
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
		return exchangeService.getHowManyDaysRemains(exchange.getApplyingDate(), exchange.getPeriod());
	}
	
	@RequestMapping(value="/getPropositionsOfExchange/{gameUserId}", method = RequestMethod.GET)
	@ResponseBody
	public List<GameUserDTO> getPropositionsOfExchange(@PathVariable Integer gameUserId) {
		Exchange exchange = exchangeService.getByGameUserId(gameUserId);
		return gamePropoService.getFromExchangeId(exchange.getId());
	}
}
