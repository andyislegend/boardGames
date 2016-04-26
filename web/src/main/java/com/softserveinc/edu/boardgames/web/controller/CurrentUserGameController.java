package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping(value = "/getAllGamesCurUser", method = RequestMethod.GET)
	@ResponseBody
	public List<GameUserDTO> showGames() {
		List<GameUserDTO> allGames = gameUserService.getGameUsersFromUsername(WebUtil.getPrincipalUsername());
		return allGames;
	}
	
	@RequestMapping(value = "NewGame", method = RequestMethod.POST)
	public String addNewGame(@RequestBody GameUserDTO gameUserDTO){	
		GameUser gameUser = new GameUser();
		gameUser = new GameUserMapper().toEntity(gameUserDTO);
		gameUser.setUser(userService.getUser(WebUtil.getPrincipalUsername()));
		gameUserService.create(gameUser);
		return "";
	}
	
	@RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> allCategories (){
		List<Category> categories = categoryService.getAll();
		return categories;
	}
	
	@RequestMapping(value = "NewComment", method = RequestMethod.POST)
	public String addComment(@RequestBody CommentsForGameDTO commentsForGameDTO) {
		CommentsForGame commentsForGame = new CommentsForGame();
		commentsForGame = new CommentForGameMapper().toEntity(commentsForGameDTO);
		commentsForGame.setUser(userService.getUser(WebUtil.getPrincipalUsername()));
		commentForGameService.addComment(commentsForGame);
		return "";
	}
	
	@RequestMapping(value = "comment/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String commentForGame(@PathVariable("id") Integer id, Model model) {
		List<CommentsForGame> commentsForGames = commentForGameService.getAllCommentsByGameId(id);
		model.addAttribute(commentsForGames);
		return "redirect:commentsForGame";
	}
}