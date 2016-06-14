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
	
	/**
	 * This method get all games for user
	 * 
	 * @return
	 */
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
	public ResponseEntity<String> addNewGame(@RequestBody GameUserDTO gameUserDTO){	
		GameUser gameUser = new GameUser();
		gameUser = GameUserMapper.toEntity(gameUserDTO);
		gameUser.setUser(userService.getUser(WebUtil.getPrincipalUsername()));
		gameUserService.add(gameUser);
		return new ResponseEntity<String>(new GameUser().toString(), HttpStatus.OK);
	}
	
	/**
	 * This method update game info
	 * 
	 * @param gameUserDTO
	 */
	@RequestMapping(value = "updateGameDetails", method = RequestMethod.PUT)
	public ResponseEntity<String> updateGame(@RequestBody GameUserDTO gameUserDTO){
		GameUser gameUser = gameUserService.getUserGamesById(gameUserDTO.getId());
		gameUser.setEdition(gameUserDTO.getEdition());
		gameUser.setYearOfProduction(gameUserDTO.getYearOfProduction());
		gameUser.setDescription(gameUserDTO.getDescription());
		gameUser.setRules(gameUserDTO.getRules());
		gameUser.setMinPlayers(gameUserDTO.getMinPlayers());
		gameUser.setMaxPlayers(gameUserDTO.getMaxPlayers());
		gameUser.setStatus(gameUserDTO.getStatus());
		gameUserService.update(gameUser);
		return new ResponseEntity<String>(new GameUser().toString(), HttpStatus.OK);

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
	 * This method delete game by id
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/deleteUserGame/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteGame(@PathVariable Integer id){
		gameUserService.deleteById(id);
		return new ResponseEntity<String>(new GameUser().toString(), HttpStatus.OK);

	}
	
	@RequestMapping(value = "/gameUserDetail/{userGameId}", method = RequestMethod.GET)
	@ResponseBody
	public GameUserDTO getGameUserDetailById(@PathVariable Integer userGameId){
		GameUserDTO dto = gameUserService.getUserGamesDTOById(userGameId);
		return dto;
	}
	
	/**
	 * This method update count of comment
	 * 
	 * @param idGame
	 * @param countOfComment
	 */
	@RequestMapping(value = "/updateCountOfComment/{idGame}/{countOfComment}",method = RequestMethod.PUT)
	public ResponseEntity<String> updateCounOfComments(@PathVariable Integer idGame, @PathVariable Integer countOfComment){
		GameUser gameUser = gameUserService.getUserGamesById(idGame);
		gameUser.setCountOfComments(countOfComment);
		gameUserService.update(gameUser);
		return new ResponseEntity<String>(new GameUser().toString(), HttpStatus.OK);

	}
	
	@RequestMapping(value = "/getCountOfTournamentsByGame/{gameId}", method = RequestMethod.GET)
	@ResponseBody
	public Integer getCountOfTournamentByGame(@PathVariable Integer gameId) {
		return gameUserService.getCountOfTournamentByGame(gameId);
	}
}