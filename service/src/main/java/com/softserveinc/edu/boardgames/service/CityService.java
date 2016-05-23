package com.softserveinc.edu.boardgames.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.softserveinc.edu.boardgames.persistence.entity.City;
import com.softserveinc.edu.boardgames.persistence.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	@Transactional
	public List<City> findAll() {
		return cityRepository.findAll();
	}
	
	public City findById(Integer id) {
		return cityRepository.findOne(id);
	}
	
	public List<City> findCitiesByCountryId(Integer countryId) {
		return cityRepository.findCitiesByCountryId(countryId);
	}
	
}