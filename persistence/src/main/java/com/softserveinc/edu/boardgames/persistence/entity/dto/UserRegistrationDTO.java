package com.softserveinc.edu.boardgames.persistence.entity.dto;

public class UserRegistrationDTO {

	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String confirmPassword;
	private String gender;
	
	public UserRegistrationDTO() {
		
	}

	public UserRegistrationDTO(String username, String email, String password, String confirmPassword, String gender) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.gender = gender;
	}

	public UserRegistrationDTO(String username, String firstName, String lastName, String email, String password,
			String confirmPassword, String gender) {
		
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
