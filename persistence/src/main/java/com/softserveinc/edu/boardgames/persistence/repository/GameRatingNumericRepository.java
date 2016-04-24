package com.softserveinc.edu.boardgames.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.GameRatingNumeric;

@Repository
public interface GameRatingNumericRepository extends JpaRepository<GameRatingNumeric, Integer>{

}
