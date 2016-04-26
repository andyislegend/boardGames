package com.softserveinc.edu.boardgames.persistence.entity.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.softserveinc.edu.boardgames.persistence.entity.CommentsForGame;
import com.softserveinc.edu.boardgames.persistence.entity.dto.CommentsForGameDTO;
import com.softserveinc.edu.boardgames.persistence.repository.GameUserRepository;

public class CommentForGameMapper implements GenericMapper<CommentsForGameDTO, CommentsForGame> {

	@Autowired
	private GameUserRepository gameUserRepository;
	
	@Override
	public CommentsForGameDTO toDTO(CommentsForGame v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentsForGame toEntity(CommentsForGameDTO t) {
		CommentsForGame commentsForGame = new CommentsForGame();
		commentsForGame.setText(t.getCommentText());
		//commentsForGame.setGameUser(gameUserService.getUserGamesById(t.getGameID()));
		return commentsForGame;
	}
}
