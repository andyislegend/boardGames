package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.Category;
import com.softserveinc.edu.boardgames.persistence.entity.CommentsForGame;
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.dto.CommentsForGameDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.CommentForGameMapper;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.GameUserMapper;
import com.softserveinc.edu.boardgames.service.CategoryService;
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
public class CurrentUserGameController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private GameUserService gameUserService;
	
	@Autowired
	private CommentForGameService commentForGameService;
	
	
	/**
	 * 	
	 * @return list of current user games
	 */
	@RequestMapping(value = "/getAllGamesCurUser", method = RequestMethod.GET)
	@ResponseBody
	public List<GameUserDTO> showGames() {
		List<GameUserDTO> allGames = gameUserService.getGameUsersFromUsername(WebUtil.getPrincipalUsername());
		return allGames;
	}
	
	/**
	 * Add new game for user
	 * 
	 * @param gameUserDTO
	 * 					 user game
	 * @return
	 */
	@RequestMapping(value = "NewGame", method = RequestMethod.POST)
	public String addNewGame(@RequestBody GameUserDTO gameUserDTO){	
		GameUser gameUser = new GameUser();
		gameUser = GameUserMapper.toEntity(gameUserDTO);
		gameUser.setUser(userService.getUser(WebUtil.getPrincipalUsername()));
		gameUserService.create(gameUser);
		return "";
	}
	
	/**
	 * Return all categories 
	 * 
	 * @return all Categories
	 */
	@RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> allCategories (){
		List<Category> categories = categoryService.getAll();
		return categories;
	}
	
	/**
	 * Add new comments for game
	 * 
	 * @param commentsForGameDTO 
	 * 							comments for game
	 * @return
	 */
	@RequestMapping(value = "NewComment", method = RequestMethod.POST)
	public String addComment(@RequestBody CommentsForGameDTO commentsForGameDTO) {
		CommentsForGame commentsForGame = new CommentsForGame();
		commentsForGame = CommentForGameMapper.toEntity(commentsForGameDTO);
		commentsForGame.setGameUser(gameUserService.getUserGamesById(commentsForGameDTO.getGameID()));
		commentsForGame.setUser(userService.getUser(WebUtil.getPrincipalUsername()));
		commentForGameService.addComment(commentsForGame);
		return "";
	}
	
	/**
	 * 
	 * @return all comments
	 */
	@RequestMapping(value = "comment", method = RequestMethod.GET)
	@ResponseBody
	public List<CommentsForGameDTO> commentsForGame() {
		List<CommentsForGameDTO> commentsForGames = commentForGameService.getAllCommentsDTO();
		return commentsForGames;
	}
	
	@RequestMapping(value = "getCountOfCommentsByGame/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Integer countOfComments(@PathVariable Integer id) {
		 return commentForGameService.getCountOfCommentsByGameId(id);
	}
}