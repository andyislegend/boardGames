package com.softserveinc.edu.boardgames.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softserveinc.edu.boardgames.persistence.entity.GameRating;

public interface GameRatingRepository extends JpaRepository<GameRating, Long>{

}
