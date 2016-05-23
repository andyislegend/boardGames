package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.List;

public class UserDTO {
	private Integer id;
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
	private List<GameUserDTO> userGames;
	private List<AllTournamentsDTO> userTournaments;
	
	public List<GameUserDTO> getUserGames() {
		return userGames;
	}
	public void setUserGames(List<GameUserDTO> userGames) {
		this.userGames = userGames;
	}
	public List<AllTournamentsDTO> getUserTournaments() {
		return userTournaments;
	}
	public void setUserTournaments(List<AllTournamentsDTO> userTournaments) {
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
