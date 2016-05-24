package com.softserveinc.edu.boardgames.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllGamesDto;
import com.softserveinc.edu.boardgames.persistence.entity.dto.ChartDTO;
import com.softserveinc.edu.boardgames.persistence.repository.GameRepository;

@Service
@Transactional
public class GameService {

	public final Integer PERCENT_ALL = 100;
	
	@Autowired
	private GameRepository gameRepo;

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
	
	public List<ChartDTO> groupGameUserByGame() {
		List<ChartDTO> listOfCharts = new ArrayList<>();
		Integer general = gameRepo.countAllUserGames();
		for (Game gu : this.getAll()) {
			ChartDTO chartDto = new ChartDTO();
			chartDto.setName(gu.getName());
			chartDto.setY(
					(new Double(gameRepo.countGameUsersOfGame(gu.getId()))/new Double(general))*PERCENT_ALL);
			if (chartDto.getY() != 0) {
				listOfCharts.add(chartDto);
			}
		}
		return listOfCharts;
	}
}
