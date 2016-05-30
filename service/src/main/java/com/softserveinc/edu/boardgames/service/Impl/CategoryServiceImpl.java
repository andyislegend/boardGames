package com.softserveinc.edu.boardgames.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Category;
import com.softserveinc.edu.boardgames.persistence.repository.CategoryRepository;
import com.softserveinc.edu.boardgames.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepo;
	
	public Category findById(Long id) {
		return categoryRepo.findOne(id);
	}
	
	public List<Category> getAll() {
		return categoryRepo.findAll();
	}
	
	@Transactional
	public void update(Category category) {
		categoryRepo.saveAndFlush(category);
	}
	
	@Transactional
	public void delete(Category category) {
		categoryRepo.delete(category);
	}
}
