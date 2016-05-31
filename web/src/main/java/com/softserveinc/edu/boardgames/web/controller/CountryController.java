package com.softserveinc.edu.boardgames.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.Country;
import com.softserveinc.edu.boardgames.service.CountryService;

/**
 * Controller for receiving info about Country entity.
 * 
 * @author Volodymyr Terlyha
 *
 */
@Controller
public class CountryController {

	@Autowired
	CountryService countrySevice;

	/**
	 * Returns all countries.
	 */
	@RequestMapping(value = {"/getAllCountries"}, method = RequestMethod.GET)
	@ResponseBody
	public List<Country> getAllCountries() {		
		return countrySevice.findAll();
	}
}