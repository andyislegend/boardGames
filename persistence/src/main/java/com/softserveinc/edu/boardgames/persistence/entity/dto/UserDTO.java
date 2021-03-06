package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.List;

public class UserDTO {
	private Integer id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String age;
	private String phoneNumber;
	private Integer countryId;
	private Integer cityId;
	private String countryName;
	private String cityName;
	private Integer userRating;
	private String level;
	private String state;
	private List<GameUserDTO> userGames;
	private List<TournamentsDTO> userTournaments;

	public UserDTO() {
	}

	public UserDTO(Integer id, String username, String firstName, String lastName, String email, String gender,
			String age, String phoneNumber, Integer userRating, String level, String state) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.userRating = userRating;
		this.level = level;
		this.state = state;
	}

	public UserDTO(Integer id, String username, String firstName, String lastName, String email, String gender,
			String age, String phoneNumber, Integer countryId, Integer cityId, String countryName, String cityName,
			Integer userRating, String level, String state) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.countryId = countryId;
		this.cityId = cityId;
		this.countryName = countryName;
		this.cityName = cityName;
		this.userRating = userRating;
		this.level = level;
		this.state = state;
	}

	public Integer getUserRating() {
		return userRating;
	}

	public void setUserRating(Integer userRating) {
		this.userRating = userRating;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<GameUserDTO> getUserGames() {
		return userGames;
	}

	public void setUserGames(List<GameUserDTO> userGames) {
		this.userGames = userGames;
	}

	public List<TournamentsDTO> getUserTournaments() {
		return userTournaments;
	}

	public void setUserTournaments(List<TournamentsDTO> userTournaments) {
		this.userTournaments = userTournaments;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}