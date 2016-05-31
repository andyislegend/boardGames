package com.softserveinc.edu.boardgames.service;

import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Image;

public interface ImageService {

	@Transactional
	public void create(Image image);

	@Transactional
	public void updateImage(Image image);

	public String findImageNameByUsername(String username);
}