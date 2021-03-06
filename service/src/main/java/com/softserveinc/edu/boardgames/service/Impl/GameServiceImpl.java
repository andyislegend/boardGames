package com.softserveinc.edu.boardgames.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllGamesDto;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameDetailsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GamesChartDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UsersAgeChartDTO;
import com.softserveinc.edu.boardgames.persistence.repository.GameRatingRepository;
import com.softserveinc.edu.boardgames.persistence.repository.GameRepository;
import com.softserveinc.edu.boardgames.service.GameService;

/**
 * @author Taras Varvariuk
 */
@Service
@Transactional
public class GameServiceImpl implements GameService{
	
	@Autowired
	private GameRepository gameRepo;
	
	@Autowired
	private GameRatingRepository gameRatingRepo;

	public Game findById(Integer id) {
		return gameRepo.findOne(id);
	}

	public List<Game> getAll() {
		return gameRepo.findAll();
	}

	@Transactional
	public void update(Game game) {
		gameRepo.saveAndFlush(game);
	}
	
	@Transactional
	public void delete(Game game) {
		gameRepo.delete(game);;
	}
	
	public List<AllGamesDto> getGamesDTO(){
		return gameRepo.getAllGames();
	}
	
	public Game findByName(String name){
        return gameRepo.findByName(name);
    }
	
	public List<GamesChartDTO> groupGameUserByGame() {
		List<GamesChartDTO> listOfCharts = new ArrayList<>();
		for (Game gu : this.getAll()) {
			GamesChartDTO chartDto = new GamesChartDTO();
			chartDto.setName(gu.getName());
			chartDto.setCountOfGames(gameRepo.countGameUsersOfGame(gu.getId()));
			listOfCharts.add(chartDto);
		}
		return listOfCharts;
	}
	
	public List<GamesChartDTO> getRatingsForGame() {
		return gameRatingRepo.getAllRatings();
	}
	
	public List<UsersAgeChartDTO> countOfUsersOfAge(){
		return gameRepo.countOfUsersOfAge();
	}

	public GameDetailsDTO getGameDetails(Integer gameId, Integer userId) {
		GameDetailsDTO game = gameRepo.getGameDetails(gameId);
		
		if (gameRatingRepo.getAverageRatingForGame(gameId) != null) {
			game.setGeneralRating(gameRatingRepo.getAverageRatingForGame(gameId));
		}
		if (gameRatingRepo.getForGameAndUser(gameId, userId) != null) {
			game.setUserRating(gameRatingRepo.getForGameAndUser(gameId, userId).intValue());
		}
		return game;
	}
}
