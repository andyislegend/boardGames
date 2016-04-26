package com.softserveinc.edu.boardgames.persistence.repository;

import com.softserveinc.edu.boardgames.persistence.entity.CommentsForGame;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CommentsForGameRepository extends JpaRepository<CommentsForGame, Integer> {
	
	@Query("select c from CommentsForGame c where c.gameUser.id = :id")
	public List<CommentsForGame> getAllCommentsForGame(@Param("id") Integer id);
	
	@Query("select count (c) from CommentsForGame c where c.id = :id")
	public Integer countsOfComment(@Param("id") Integer id);
}
