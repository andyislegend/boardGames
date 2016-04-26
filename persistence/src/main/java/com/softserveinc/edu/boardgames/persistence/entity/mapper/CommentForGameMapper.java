package com.softserveinc.edu.boardgames.persistence.entity.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.softserveinc.edu.boardgames.persistence.entity.CommentsForGame;
import com.softserveinc.edu.boardgames.persistence.entity.dto.CommentsForGameDTO;
import com.softserveinc.edu.boardgames.persistence.repository.GameUserRepository;

public class CommentForGameMapper {

	@Autowired
	private GameUserRepository gameUserRepository;
	
	
	public static CommentsForGameDTO toDTO(CommentsForGame v) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static CommentsForGame toEntity(CommentsForGameDTO t) {
		CommentsForGame commentsForGame = new CommentsForGame();
		commentsForGame.setText(t.getCommentText());
		//commentsForGame.setGameUser(gameUserService.getUserGamesById(t.getGameID()));
		return commentsForGame;
	}
}
