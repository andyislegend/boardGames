package com.softserveinc.edu.boardgames.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.softserveinc.edu.boardgames.persistence.entity.Country;

public interface CountryService {

	@Transactional
	public List<Country> findAll();

	public Country findById(Integer id);

}