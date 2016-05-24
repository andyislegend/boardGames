package com.softserveinc.edu.boardgames.persistence.entity.mapper;

import com.softserveinc.edu.boardgames.persistence.entity.City;
import com.softserveinc.edu.boardgames.persistence.entity.Country;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.UserDTO;

public class UserMapper {
	public static User toEntity(UserDTO userDTO, User user, Country country, City city) {
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setGender(userDTO.getGender());
		user.setAge(userDTO.getAge());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setCountry(country);
		user.setCity(city);
		return user;
	}

}
