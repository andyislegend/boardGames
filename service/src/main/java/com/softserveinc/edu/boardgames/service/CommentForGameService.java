package com.softserveinc.edu.boardgames.service;

import java.util.List;

import com.softserveinc.edu.boardgames.persistence.entity.CommentsForGame;
import com.softserveinc.edu.boardgames.persistence.entity.dto.CommentsForGameDTO;

public interface CommentForGameService {
	
	public CommentsForGame getCommentById(Integer id);
	
	public List<CommentsForGame> getAllComments();
	
	public void update(CommentsForGame commentsForGame);
	
	public void addComment(CommentsForGame commentsForGame);
	
	public List<CommentsForGameDTO> getAllCommentsByGameId(Integer id);
	
	public Integer getCountOfCommentsByGameId(Integer id);
	
	public List<CommentsForGameDTO> getAllCommentsDTO();

}
