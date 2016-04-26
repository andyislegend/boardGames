package com.softserveinc.edu.boardgames.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.softserveinc.edu.boardgames.persistence.entity.User;

@Configuration
@PropertySource(value = "classpath:/properties/image.properties")
public class ImageConfiguration {

	@Value("${avatarUrl}")
	private String avatarUrl;
	
	@Value("${gamesUrl}")
	private String gamesUrl;
	
	@Value("${avatarPackage}")
	private String avatarPackage;
	
	@Value("${gamesUrl}")
	private String gamesPackage;
	
	public String getAvatarUrl(User user) {
		return avatarUrl + user.getUsername();
	}
	
	public String getGamesUrl(User user) {
		return gamesUrl + user.getUsername();
	}
	public String getAvatarPackage(User user) {
		return avatarPackage + user.getUsername();
	}
	
	public String getGamesPackage(User user) {
		return gamesPackage + user.getUsername();
	}
	
}
