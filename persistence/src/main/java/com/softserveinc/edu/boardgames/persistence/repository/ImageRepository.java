package com.softserveinc.edu.boardgames.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
	
	@Query("Select i.imageName FROM Image i WHERE i.user.username = :username")
	public String findImageNameByUsername(@Param("username") String username);
	
	public Image findByImageLocation(String imageLocation);
}
