package com.softserveinc.edu.boardgames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.repository.UserRatingRepository;

@Service
@Transactional
public class UserRatingService {

	@Autowired
	private UserRatingRepository usrRatingRepository;

}
