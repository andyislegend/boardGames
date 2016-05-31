package com.softserveinc.edu.boardgames.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Image;
import com.softserveinc.edu.boardgames.persistence.repository.ImageRepository;
import com.softserveinc.edu.boardgames.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepository;

	@Override
	@Transactional
	public void create(Image image) {
		imageRepository.save(image);
	}

	@Override
	@Transactional
	public void updateImage(Image image) {
		imageRepository.saveAndFlush(image);
	}

	@Override
	public String findImageNameByUsername(String username) {
		return imageRepository.findImageNameByUsername(username);
	}
}