package com.softserveinc.edu.boardgames.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softserveinc.edu.boardgames.persistence.entity.UserRating;

public interface UserRatingRepository extends JpaRepository<UserRating, Long>{

}
