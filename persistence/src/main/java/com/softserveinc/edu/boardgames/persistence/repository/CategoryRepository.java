package com.softserveinc.edu.boardgames.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Category;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long>{

}
