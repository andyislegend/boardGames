package com.softserveinc.edu.boardgames.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.softserveinc.edu.boardgames.persistence.entity.Country;
import com.softserveinc.edu.boardgames.persistence.repository.CountryRepository;
import com.softserveinc.edu.boardgames.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Override
	@Transactional
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	@Override
	public Country findById(Integer id) {
		return countryRepository.findOne(id);
	}

}
