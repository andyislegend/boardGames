package com.softserveinc.edu.boardgames.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.softserveinc.edu.boardgames.persistence.entity.City;

public interface CityService {

	@Transactional
	public List<City> findAll();
	
	public City findById(Integer id);
	
	public List<City> findCitiesByCountryId(Integer countryId);
}