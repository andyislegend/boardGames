package com.softserveinc.edu.boardgames.persistence.repository;

import com.softserveinc.edu.boardgames.persistence.entity.CommentsForGame;
import com.softserveinc.edu.boardgames.persistence.entity.dto.CommentsForGameDTO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CommentsForGameRepository extends JpaRepository<CommentsForGame, Integer> {
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.CommentsForGameDTO(c.gameUser.id, c.text, c.user.username, c.date) from CommentsForGame c where c.gameUser.id = :id")
	public List<CommentsForGameDTO> getAllCommentsForGame(@Param("id") Integer id);
	
	@Modifying
	@Query("delete from CommentsForGame c where c.gameUser.id = :id")
	public void deleteByGameUser(@Param("id")Integer id);
	
	@Query("select count (c) from CommentsForGame c where c.gameUser.id = :id")
	public Integer countsOfComment(@Param("id") Integer id);
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.CommentsForGameDTO(c.gameUser.id, c.text, c.user.username, c.date) from CommentsForGame c")
	public List<CommentsForGameDTO> getAllCoomentsDTO();
}
