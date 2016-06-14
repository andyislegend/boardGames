package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.CommentsForGame;
import com.softserveinc.edu.boardgames.persistence.entity.dto.CommentsForGameDTO;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.CommentForGameMapper;
import com.softserveinc.edu.boardgames.service.CommentForGameService;
import com.softserveinc.edu.boardgames.service.GameUserService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

/**
 * 
 * @author Volodymyr Krokhmaliuk
 *
 */
@Controller
public class CommentsController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GameUserService gameUserService;
	
	@Autowired
	private CommentForGameService commentForGameService;
	
	/**
	 * Add new comments for game
	 * 
	 * @param commentsForGameDTO 
	 * 							comments for game
	 * @return
	 */
	@RequestMapping(value = "newComment", method = RequestMethod.POST)
	public ResponseEntity<String> addCommentForUserGame(@RequestBody CommentsForGameDTO commentsForGameDTO) {
		CommentsForGame commentsForGame = new CommentsForGame();
		commentsForGame = CommentForGameMapper.toEntity(commentsForGameDTO);
		commentsForGame.setGameUser(gameUserService.getUserGamesById(commentsForGameDTO.getGameID()));
		commentsForGame.setUser(userService.getUser(WebUtil.getPrincipalUsername()));
		commentForGameService.addComment(commentsForGame);
		return new ResponseEntity<String>(new CommentsForGame().toString(), HttpStatus.OK);
		   
		
	}
	
	@RequestMapping(value = "/getCommentsForGame/{gameId}", method = RequestMethod.GET)
	@ResponseBody
	public List<CommentsForGameDTO> getCommentsForGame(@PathVariable Integer gameId ){
		List<CommentsForGameDTO> commentsForGameDTOs = commentForGameService.getAllCommentsByGameId(gameId);
		return commentsForGameDTOs ;
	}
	
	@RequestMapping(value = "/comment", method = RequestMethod.GET)
	@ResponseBody
	public List<CommentsForGameDTO> getAllComments() {
		List<CommentsForGameDTO> commentsForGames = commentForGameService.getAllCommentsDTO();
		return commentsForGames;
	}
	
	@RequestMapping(value = "/getCountOfCommentsByGameId/{gameUserId}", method = RequestMethod.GET)
	@ResponseBody
	public Integer getCountOfCommentsForGameById(@PathVariable Integer gameUserId){
		int countOfComments = commentForGameService.getCountOfCommentsByGameId(gameUserId);
		return countOfComments;
	}
}
