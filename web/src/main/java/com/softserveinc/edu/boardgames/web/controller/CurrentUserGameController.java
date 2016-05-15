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
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.GameUserMapper;
import com.softserveinc.edu.boardgames.service.CategoryService;
import com.softserveinc.edu.boardgames.service.ExchangeService;
import com.softserveinc.edu.boardgames.service.GameService;
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
	private GameService gameService;
	
	@Autowired
	private ExchangeService exchangeService;
	
	/**
	 * 	
	 * @return list of current user games
	 */
	@RequestMapping(value = "/getAllMyGamesCurUser", method = RequestMethod.GET)
	@ResponseBody
	public List<GameUserDTO> showGames() {
		List<GameUserDTO> allGames = gameUserService
				.getMyGameUsersFromUsername(WebUtil.getPrincipalUsername());
		return allGames;
	}
	
	@RequestMapping(value = "/getAllSharedGamesCurUser", method = RequestMethod.GET)
	@ResponseBody
	public List<GameUserDTO> showSharedGames() {
		List<GameUserDTO> sharedGames = gameUserService
				.getSharedGameUsersFromUsername(WebUtil.getPrincipalUsername());
		return sharedGames;
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
		gameUserService.add(gameUser);
		return "";
	}
	
	@RequestMapping(value = "updateGameDetails", method = RequestMethod.PUT)
	public String updateGame(@RequestBody GameUserDTO gameUserDTO){
		GameUser gameUser = gameUserService.getUserGamesById(gameUserDTO.getId());
		gameUser.setEdition(gameUserDTO.getEdition());
		gameUser.setYearOfProduction(gameUserDTO.getYearOfProduction());
		gameUser.setStatus(gameUserDTO.getStatus());
		gameUser.setUser(userService.getUser(WebUtil.getPrincipalUsername()));
		gameUserService.update(gameUser);
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
	
	@RequestMapping(value = "/deleteUserGame/{id}",method = RequestMethod.DELETE)
	public String deleteGame(@PathVariable Integer id){
		gameUserService.deleteById(id);
		return"";
	}
	
	@RequestMapping(value = "/gameUserDetail/{userGameId}", method = RequestMethod.GET)
	@ResponseBody
	public GameUserDTO getGameUserDetailById(@PathVariable Integer userGameId){
		GameUserDTO dto = gameUserService.getUserGamesDTOById(userGameId);
		return dto;
	}
	
	@RequestMapping(value = "/updateCountOfComment/{idGame}/{countOfComment}",method = RequestMethod.PUT)
	public String updateCounOfComments(@PathVariable Integer idGame, @PathVariable Integer countOfComment){
		GameUser gameUser = gameUserService.getUserGamesById(idGame);
		gameUser.setCountOfComments(countOfComment);
		gameUserService.update(gameUser);
		return "";
	}
}