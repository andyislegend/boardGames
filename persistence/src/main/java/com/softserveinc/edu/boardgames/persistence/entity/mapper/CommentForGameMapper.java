package com.softserveinc.edu.boardgames.persistence.entity.mapper;

import com.softserveinc.edu.boardgames.persistence.entity.CommentsForGame;
import com.softserveinc.edu.boardgames.persistence.entity.dto.CommentsForGameDTO;

public class CommentForGameMapper {
		
	public static CommentsForGameDTO toDTO(CommentsForGame v) {
		CommentsForGameDTO commentsForGameDTO = new CommentsForGameDTO();
		commentsForGameDTO.setGameID(v.getGameUser().getId());
		commentsForGameDTO.setCommentText(v.getText());
		return commentsForGameDTO;
	}
	
	public static CommentsForGame toEntity(CommentsForGameDTO t) {
		CommentsForGame commentsForGame = new CommentsForGame();
		commentsForGame.setText(t.getCommentText());
		return commentsForGame;
	}
}
