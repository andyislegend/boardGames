package com.softserveinc.edu.boardgames.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.edu.boardgames.web.util.WebUtil;

/**
 * 
 * @author Andrii Petryk
 * 
 *         Controller class that catches request to index page and home page
 *
 */
@Controller
public class IndexController {

	/**
	 * Represent path to index.jsp page with login and registration options
	 */
	private static final String INDEX_PAGE = "index";

	/**
	 * Represent path to home.jsp page available after successful log in
	 */
	private final static String HOME_PAGE = "home";

	/**
	 * Method which redirects you to index page
	 * 
	 * @return index.jsp
	 */
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public final String getIndexPage() {
		return INDEX_PAGE;
	}

	/**
	 * Method which redirects you to home page, also add currently logged user
	 * to model
	 * 
	 * @param model
	 * @return home.jsp
	 */
	@RequestMapping(value = "/home**", method = RequestMethod.GET)
	public final String getUserPage(Model model) {
		model.addAttribute("user", WebUtil.getPrincipalUsername());

		return HOME_PAGE;
	}

}
