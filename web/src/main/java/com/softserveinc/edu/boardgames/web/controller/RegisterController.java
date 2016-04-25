package com.softserveinc.edu.boardgames.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.edu.boardgames.persistence.entity.Address;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	UserService userService;
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		Address address = new Address();
		model.addAttribute("user", user);
		model.addAttribute("address", address);
		return "registration";
	}

	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user,@RequestParam("confirmPassword") String confirmPassword, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		if (userService.isExistsWithUsername(user.getUsername())) {

			FieldError usernameError = new FieldError("user", "username",
					"Sorry, but this usernmae is already taken. Choose another one");
			result.addError(usernameError);
			return "registration";
		}
		
		if (!user.getPassword().equals(confirmPassword)) {

			FieldError passwordError = new FieldError("user", "password",
					"Sorry, but You must confirm Your password");
			result.addError(passwordError);
			return "registration";
		}
		
		if (userService.isExistsWithEmail(user.getEmail())) {

			FieldError emailError = new FieldError("user", "email",
					"Sorry, but this email is already in use!");
			result.addError(emailError);
			return "registration";
		}
		
		if (!validate(user.getEmail())) {

			FieldError emailError = new FieldError("user", "email",
					"Sorry, but Your email seems to be wrong");
			result.addError(emailError);
			return "registration";
		}
		

		userService.createUser(user);

		return "index";
	}

	private static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
}

	
}