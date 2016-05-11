package com.softserveinc.edu.boardgames.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.GameUserMapper;
import com.softserveinc.edu.boardgames.persistence.repository.GameUserRepository;

@RunWith(MockitoJUnitRunner.class)
public class GameUserServiceTest {

	@Mock
	private GameUserRepository gameUserRepository;
	
	@InjectMocks
	private GameUserService gameUserService;
	
	@Test
	public void testGameGetById(){
		Game game = new Game();
		game.setName("Domino");
		
		GameUser gameUser = new GameUser();
		gameUser.setId(0);
		gameUser.setGame(game);
		gameUser.setEdition("main edition");
		
		gameUserService.add(gameUser);
		when(gameUserRepository.getGameUserById(0)).thenReturn(gameUser);
		GameUser user = gameUserService.getUserGamesById(0);
		verify(gameUserRepository).getGameUserById(0);
		assertEquals(gameUser, user);
	}
	
	@Test
	public void testGameUserUpdate(){
		Game game = new Game();
		game.setName("Domino");
		
		GameUser gameUser = new GameUser();
		gameUser.setId(0);
		gameUser.setGame(game);
		gameUser.setEdition("main edition");
		
		gameUserService.add(gameUser);
		when(gameUserService.getUserGamesById(0)).thenReturn(gameUser);
		gameUser = gameUserService.getUserGamesById(0);
		gameUser.setEdition("new edition");
		gameUserService.update(gameUser);
		
		when(gameUserService.getUserGamesById(0)).thenReturn(gameUser);
		gameUser = gameUserService.getUserGamesById(0);
		
		assertEquals("new edition", gameUser.getEdition());
	}
	
	@Test
	public void getAllGames() {
		List<GameUserDTO> dtos = new ArrayList<>();
		
		GameUserDTO gameUser = new GameUserDTO();
		gameUser.setId(0);
		gameUser.setName("Domino");
		gameUser.setEdition("main edition");		
		gameUserService.add(GameUserMapper.toEntity(gameUser));
		
		dtos.add(gameUser);
		
		when(gameUserService.getGameUsersByName("Domino")).thenReturn(dtos);
		List<GameUserDTO> gameUserDTOs = gameUserService.getGameUsersByName("Domino");
		
		verify(gameUserRepository).getGameUserByName("Domino");
		assertEquals(dtos, gameUserDTOs);
	}
}
