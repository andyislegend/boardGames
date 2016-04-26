package com.softserveinc.edu.boardgames.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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

	@Value("${defaultMaleAvatarUrl}")
	private String defaultMaleAvatarUrl;
	
	@Value("${defaultFemaleAvatarUrl}")
	private String defaultFemaleAvatarUrl;

	public String getAvatarUrl(String username) {
		return avatarUrl + username;
	}

	public String getGamesUrl(String username) {
		return gamesUrl + username;
	}

	public String getAvatarPackage(String username) {
		return avatarPackage + username;
	}

	public String getGamesPackage(String username) {
		return gamesPackage + username;
	}

	public String getDefaultMaleAvatarUrl() {
		return defaultMaleAvatarUrl;
	}
	
	public String getDefaultFemaleAvatarUrl() {
		return defaultFemaleAvatarUrl;
	}
}