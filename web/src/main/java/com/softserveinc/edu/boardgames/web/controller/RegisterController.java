package com.softserveinc.edu.boardgames.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.softserveinc.edu.boardgames.persistence.entity.Address;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	UserService userService;

	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		Address address = new Address();
		model.addAttribute("user", user);
		model.addAttribute("address", address);
		return "registration";
	}

	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		if (userService.isExistsWithUsername(user.getUsername())) {

			FieldError usernameError = new FieldError("user", "username",
					"Sorry, but this usernmae is already taken. Choose another one");
			result.addError(usernameError);
			return "registration";
		}

		userService.createUser(user);
		;

		model.addAttribute("success", "User ---" + user.getUsername() + "--- registered successfully");
		return "login";
	}

}
