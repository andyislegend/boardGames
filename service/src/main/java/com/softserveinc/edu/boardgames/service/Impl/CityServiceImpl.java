package com.softserveinc.edu.boardgames.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.softserveinc.edu.boardgames.persistence.entity.City;
import com.softserveinc.edu.boardgames.persistence.repository.CityRepository;
import com.softserveinc.edu.boardgames.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Override
	@Transactional
	public List<City> findAll() {
		return cityRepository.findAll();
	}
	
	@Override
	public City findById(Integer id) {
		return cityRepository.findOne(id);
	}
	
	@Override
	public List<City> findCitiesByCountryId(Integer countryId) {
		return cityRepository.findCitiesByCountryId(countryId);
	}
	
}