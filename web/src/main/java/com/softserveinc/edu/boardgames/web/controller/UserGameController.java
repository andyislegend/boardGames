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
import com.softserveinc.edu.boardgames.persistence.entity.dto.InfoFromApplierDTO;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.GameUserMapper;
import com.softserveinc.edu.boardgames.service.CategoryService;
import com.softserveinc.edu.boardgames.service.ExchangeService;
import com.softserveinc.edu.boardgames.service.GamePropositionService;
import com.softserveinc.edu.boardgames.service.GameUserService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

/**
 * 
 * @author Volodymyr Krokhmaliuk
 *
 */
@Controller
public class UserGameController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private GameUserService gameUserService;
	
	@Autowired
	private ExchangeService exchangeService;
	
	@Autowired
	private GamePropositionService gamePropoService;
	
	/**
	 * 	
	 * @return list of current user games
	 */
	@RequestMapping(value = "/getAllMyGamesCurUser", method = RequestMethod.GET)
	@ResponseBody
	public List<GameUserDTO> getUserGames() {		
		return gameUserService.getMyGameUsersFromUsername(WebUtil.getPrincipalUsername());
	}
	
	@RequestMapping(value = "/getAllUserGames")
	@ResponseBody
	public List<GameUserDTO> getAllUserGames() {
		return gameUserService.getAllUsersGame();
	}
	
	@RequestMapping(value = "/getAllSharedGamesCurUser", method = RequestMethod.GET)
	@ResponseBody
	public List<GameUserDTO> showSharedGames() {
		List<GameUserDTO> sharedGames = gameUserService
				.getSharedGameUsersFromUsername(WebUtil.getPrincipalUsername());
		return sharedGames;
	}
	
	@RequestMapping(value = "/getAllBorrowedGamesCurUser", method = RequestMethod.GET)
	@ResponseBody
	public List<InfoFromApplierDTO> showBorrowedGames() {
		List<InfoFromApplierDTO> borrowedGames = 
			exchangeService.getAllBorrowedGames(
					userService.getUser(WebUtil.getPrincipalUsername()).getId());
		borrowedGames.addAll(gamePropoService.getAllGamePropositionsForUser(
				userService.getUser(WebUtil.getPrincipalUsername()).getId()));
		return borrowedGames;
	}
	
	/**
	 * Add new game for user
	 * 
	 * @param gameUserDTO, user game
	 * @return
	 */
	@RequestMapping(value = "NewGame", method = RequestMethod.POST)
	public void addNewGame(@RequestBody GameUserDTO gameUserDTO){	
		GameUser gameUser = new GameUser();
		gameUser = GameUserMapper.toEntity(gameUserDTO);
		gameUser.setUser(userService.getUser(WebUtil.getPrincipalUsername()));
		gameUserService.add(gameUser);
	}
	
	@RequestMapping(value = "updateGameDetails", method = RequestMethod.PUT)
	public void updateGame(@RequestBody GameUserDTO gameUserDTO){
		GameUser gameUser = gameUserService.getUserGamesById(gameUserDTO.getId());
		gameUser.setEdition(gameUserDTO.getEdition());
		gameUser.setYearOfProduction(gameUserDTO.getYearOfProduction());
		gameUser.setStatus(gameUserDTO.getStatus());
		gameUserService.update(gameUser);
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
	public void deleteGame(@PathVariable Integer id){
		gameUserService.deleteById(id);
	}
	
	@RequestMapping(value = "/gameUserDetail/{userGameId}", method = RequestMethod.GET)
	@ResponseBody
	public GameUserDTO getGameUserDetailById(@PathVariable Integer userGameId){
		GameUserDTO dto = gameUserService.getUserGamesDTOById(userGameId);
		return dto;
	}
	
	@RequestMapping(value = "/updateCountOfComment/{idGame}/{countOfComment}",method = RequestMethod.PUT)
	public void updateCounOfComments(@PathVariable Integer idGame, @PathVariable Integer countOfComment){
		GameUser gameUser = gameUserService.getUserGamesById(idGame);
		gameUser.setCountOfComments(countOfComment);
		gameUserService.update(gameUser);
	}
	
	@RequestMapping(value = "/getCountOfTournamentsByGame/{gameId}", method = RequestMethod.GET)
	@ResponseBody
	public Integer getCountOfTournamentByGame(@PathVariable Integer gameId) {
		return gameUserService.getCountOfTournamentByGame(gameId);
	}
}