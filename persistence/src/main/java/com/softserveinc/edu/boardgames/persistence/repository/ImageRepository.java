package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.GameUser;
import com.softserveinc.edu.boardgames.persistence.entity.Image;
import com.softserveinc.edu.boardgames.persistence.entity.User;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
	
	@Query("Select i.url FROM Image i WHERE i.user.username =:username")
	public String findUrlByUsername(@Param("username") String username);

}
