package com.softserveinc.edu.boardgames.persistence.repository;

import com.softserveinc.edu.boardgames.persistence.entity.CommentsForGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CommentsForGameRepository extends JpaRepository<CommentsForGame, Integer> {

}
