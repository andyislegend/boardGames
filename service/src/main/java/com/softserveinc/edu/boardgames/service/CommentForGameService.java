package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.CommentsForGame;
import com.softserveinc.edu.boardgames.persistence.repository.CommentsForGameRepository;



@Service
@Transactional
public class CommentForGameService {
	
	@Autowired
	private CommentsForGameRepository commentsForGameRepository;
	
	public CommentsForGame getCommentById(Integer id){
		return commentsForGameRepository.findOne(id);
	}
	
	public List<CommentsForGame> getAllComments() {
		return commentsForGameRepository.findAll();
	}
	
	@Transactional
	public void update(CommentsForGame commentsForGame) {
		commentsForGameRepository.saveAndFlush(commentsForGame);
	}
	
	@Transactional
	public void addComment(CommentsForGame commentsForGame) {
		commentsForGameRepository.save(commentsForGame);
	}
	
	public List<CommentsForGame> getAllCommentsByGameId(Integer id){
		return commentsForGameRepository.getAllCommentsForGame(id);
	}
	
	public List<CommentsForGame> getAllCommentsByGameAndUser(Integer gameId, Integer userId){
		return commentsForGameRepository.getAllCommentsForGameByUser(gameId, userId);
	}
}
