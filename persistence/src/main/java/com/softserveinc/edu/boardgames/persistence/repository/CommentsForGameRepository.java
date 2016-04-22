package com.softserveinc.edu.boardgames.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.ÑommentsForGame;

@Repository
@Transactional
public interface CommentsForGameRepository extends JpaRepository<ÑommentsForGame, Integer> {

}
