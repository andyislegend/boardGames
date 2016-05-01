package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.City;
import com.softserveinc.edu.boardgames.service.CityService;

/**
 * Controller for receiving info about City entity.
 * 
 * @author Volodymyr Terlyha
 *
 */
@Controller
public class CityController {

	@Autowired
	CityService citySevice;

	/**
	 * Returns all cities in a country.
	 */
	@RequestMapping(value = {"/getAllCities"}, method = RequestMethod.GET)
	@ResponseBody
	public List<City> getAllUsers(@RequestParam("countryId") Integer countryId) {
		List<City> cityList = citySevice.findCitiesByCountryId(countryId);
		return cityList;
	}
}