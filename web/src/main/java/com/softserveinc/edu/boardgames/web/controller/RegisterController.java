package com.softserveinc.edu.boardgames.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserPasswordDTO;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.service.util.OnRegistrationCompleteEvent;
import com.softserveinc.edu.boardgames.web.util.WebUtil;

/**
 * This conrollers is responsible for add new user and validating field in
 * registration process.
 * 
 * @param username
 *            must be unique.
 * @param email
 *            must be unique and be a valid mail address.
 * @param password
 *            must be equals to @param confirm password
 * 
 * @author Andrii Petryk
 *
 */
@Controller
public class RegisterController {

	@Autowired
	UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	/**
	 * @param INVALID_TOKEN_MAIL_CONFIRMATION
	 *            is used to validate verification token
	 * 
	 */
	private final static String INVALID_TOKEN_MAIL_CONFIRMATION = "invalid";

	/**
	 * @param VALID_EMAIL_ADDRESS_REGEX
	 *            is used to validate the correctness of mail provided by new
	 *            user during registration
	 */
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	/**
	 * @param VALID_PSASSWORD_REGEX
	 *            is used to validate the safety of users password
	 */
	private static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})");

	/**
	 * @param VALID_USERNAME_REGEX
	 *            is used to validate the safety of username
	 */
	private static final Pattern VALID_USERNAME_REGEX = Pattern.compile("^[a-zA-z0-9_-]{3,9}");

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param gender
	 * @param username
	 * @param password
	 * @param confirmPassword
	 * @param request
	 * @return ResponseEntity with HttpStatus.CONFLICT or HttpStatus.OK
	 * 
	 *         Controller that validate registration form and return
	 *         HttpStatus.CONFLICT with error message if there is invalid data
	 *         in fields provided by user or return HttpStatus.OK if
	 *         registration was successful
	 */
	@RequestMapping(value = { "/addNewUser" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addNewUser(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("gender") String gender, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword,
			final HttpServletRequest request) {

		if (username.isEmpty() || gender.isEmpty() || email.isEmpty() || password.isEmpty()) {

			return new ResponseEntity<String>("Fields marked with \"*\" are required. Please enter valid data.",
					HttpStatus.CONFLICT);

		}

		if (!validateUsername(username)) {

			return new ResponseEntity<String>("Sorry, but Username must contain from 3 to 9 symbols.",
					HttpStatus.CONFLICT);
		}

		if (userService.isExistsWithUsername(username)) {

			return new ResponseEntity<String>("Sorry, but this Usernmae is already taken. Choose another one!",
					HttpStatus.CONFLICT);

		}

		if (!validateMail(email)) {

			return new ResponseEntity<String>("Please enter a valid email address", HttpStatus.CONFLICT);

		}

		if (userService.isExistsWithEmail(email)) {

			return new ResponseEntity<String>("Sorry, but this email is already in use!", HttpStatus.CONFLICT);

		}

		if (!validatePassword(password)) {

			return new ResponseEntity<String>(
					"Password must contain from 6 to 20 symbols with at least 1 upper case symbol and 1 number",
					HttpStatus.CONFLICT);

		}

		if (!password.equals(confirmPassword)) {

			return new ResponseEntity<String>("Sorry, but You must confirm Your password", HttpStatus.CONFLICT);

		}

		User newUser = new User();
		newUser.setEmail(email);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setGender(gender);
		userService.createUser(newUser);

		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(newUser, getAppUrl(request)));

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param model
	 * @param token
	 * @return userifo page
	 * 
	 *         Controller that is used to validate token from confirmation link
	 */
	@RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
	public String confirmRegistration(Model model, @RequestParam("token") final String token) {
		final String result = userService.validateVerificationToken(token);

		if (result == null) {
			model.addAttribute("success", true);
		}

		if (result.equals(INVALID_TOKEN_MAIL_CONFIRMATION)) {
			model.addAttribute("expired", true);
		}

		return "userinfo";
	}

	@RequestMapping(value = { "/updateUserPassword" }, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateUserPassword(@RequestBody UserPasswordDTO userPasswordDTO) {
		User user = userService.findOne(WebUtil.getPrincipalUsername());
		if (userPasswordDTO.getOldPassword() == null || userPasswordDTO.getNewPassword() == null 
				|| userPasswordDTO.getConfirmPassword() == null) {
				return new ResponseEntity<String>(HttpStatus.CONFLICT);
		} else if (!passwordEncoder.matches(userPasswordDTO.getOldPassword(), user.getPassword())) {
			return new ResponseEntity<String>("OLD_PASSWORD_ANSWER", HttpStatus.CONFLICT);
		} else if (!validatePassword(userPasswordDTO.getNewPassword())) {
			return new ResponseEntity<String>("NEW_PASSWORD_ANSWER", HttpStatus.CONFLICT);
		} else if (!userPasswordDTO.getNewPassword().equals(userPasswordDTO.getConfirmPassword())) {
			return new ResponseEntity<String>("CONFIRM_PASSWORD_ANSWER", HttpStatus.CONFLICT);
		}		
		user.setPassword(passwordEncoder.encode(userPasswordDTO.getNewPassword()));
		userService.updateUser(user);
		return new ResponseEntity<String>("CHANGES_SAVED", HttpStatus.OK);
	}

	private static boolean validateMail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	private static boolean validatePassword(String password) {
		Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
		return matcher.find();
	}

	private static boolean validateUsername(String username) {
		Matcher matcher = VALID_USERNAME_REGEX.matcher(username);
		return matcher.find();
	}

	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

}