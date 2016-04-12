package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Category;
import com.softserveinc.edu.boardgames.persistence.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	public Category findById(Long id) {
		return categoryRepo.findOne(id);
	}
	
	public List<Category> getAll(){
		return categoryRepo.findAll();
	}
	
	@Transactional
	public void update(Category category) {
		categoryRepo.saveAndFlush(category);
	}
	
	@Transactional
	public void create(Category category) {
		categoryRepo.save(category);
	}
}
