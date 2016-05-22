package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.Set;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.entity.Tournament;

public class UserDTO {
	private Integer id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private Integer age;
	private String phoneNumber;
	private Integer countryId;
	private Integer cityId;
	private String countryName;
	private String cityName;
	private Set <Game> userGames;
	private Set <Tournament> userTournaments;
	
	public UserDTO() {	
	}
	
	public UserDTO(Integer id, String username, String firstName, String lastName, String email, String gender,
			Integer age, String phoneNumber, String countryName, String cityName, Set<Game> userGames,
			Set<Tournament> userTournaments) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.countryName = countryName;
		this.cityName = cityName;
		this.userGames = userGames;
		this.userTournaments = userTournaments;
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
	public Set<Game> getUserGames() {
		return userGames;
	}
	public void setUserGames(Set<Game> userGames) {
		this.userGames = userGames;
	}
	public Set<Tournament> getUserTournaments() {
		return userTournaments;
	}
	public void setUserTournaments(Set<Tournament> userTournaments) {
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}	
}
