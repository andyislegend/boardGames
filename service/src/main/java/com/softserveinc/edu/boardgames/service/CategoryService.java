package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Category;

public interface CategoryService {

	Category findById(Long id);
	
	List<Category> getAll();
	
	@Transactional
	void update(Category category);
	
	@Transactional
	void delete(Category category);
}
