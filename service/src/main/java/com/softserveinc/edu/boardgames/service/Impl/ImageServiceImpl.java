package com.softserveinc.edu.boardgames.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Image;
import com.softserveinc.edu.boardgames.persistence.repository.ImageRepository;

@Service
public class ImageServiceImpl {

	@Autowired
	private ImageRepository imageRepository;

	@Transactional
	public void create(Image image) {
		imageRepository.save(image);
	}

	@Transactional
	public void updateImage(Image image) {
		imageRepository.saveAndFlush(image);
	}

	public String findImageNameByUsername(String username) {
		return imageRepository.findImageNameByUsername(username);
	}
}