package com.softserveinc.edu.boardgames.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.CommentsForGame;
import com.softserveinc.edu.boardgames.persistence.entity.dto.CommentsForGameDTO;
import com.softserveinc.edu.boardgames.persistence.repository.CommentsForGameRepository;
import com.softserveinc.edu.boardgames.service.CommentForGameService;



@Service
@Transactional
public class CommentForGameServiceImpl implements CommentForGameService {
	
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
	
	public List<CommentsForGameDTO> getAllCommentsByGameId(Integer id){
		return commentsForGameRepository.getAllCommentsForGame(id);
	}
	
	public Integer getCountOfCommentsByGameId(Integer id) {
		return commentsForGameRepository.countsOfComment(id);
	}
	
	public List<CommentsForGameDTO> getAllCommentsDTO(){
		return commentsForGameRepository.getAllCoomentsDTO();
	}
}
