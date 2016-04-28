package com.softserveinc.edu.boardgames.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration class is responsible for getting url and physical location of
 * images uploaded by users
 */
@Configuration
@PropertySource(value = "classpath:/properties/image.properties")
public class ImageConfiguration {

	/**
	 * Describes url to users avatar package.
	 */
	@Value("${avatarUrl}")
	private String avatarUrl;

	/**
	 * Describes url to users game images package.
	 */
	@Value("${gamesUrl}")
	private String gamesUrl;

	/**
	 * Describes physical address of avatar storage package.
	 */
	@Value("${avatarPackage}")
	private String avatarPackage;

	/**
	 * Describes physical address of games images storage package.
	 */
	@Value("${gamesUrl}")
	private String gamesPackage;

	/**
	 * Describes url to default male avatar.
	 */
	@Value("${defaultMaleAvatarUrl}")
	private String defaultMaleAvatarUrl;

	/**
	 * Describes url to default female avatar.
	 */
	@Value("${defaultFemaleAvatarUrl}")
	private String defaultFemaleAvatarUrl;

	/**
	 * Get value of users avatar url.
	 * 
	 * @return value users avatar url.
	 */
	public String getAvatarUrl(String username) {
		return avatarUrl + username;
	}

	/**
	 * Get value of games images url.
	 * 
	 * @return value games images url.
	 */
	public String getGamesUrl(String username) {
		return gamesUrl + username;
	}

	/**
	 * Get value of physical address of avatar storage package.
	 * 
	 * @return value of physical address of avatar storage package.
	 */
	public String getAvatarPackage(String username) {
		return avatarPackage + username;
	}

	/**
	 * Get value of physical address of image games storage package.
	 * 
	 * @return value of physical address of image games storage package.
	 */
	public String getGamesPackage(String username) {
		return gamesPackage + username;
	}

	/**
	 * Get value of default male avatar url.
	 * 
	 * @return value of default male avatar url.
	 */
	public String getDefaultMaleAvatarUrl() {
		return defaultMaleAvatarUrl;
	}

	/**
	 * Get value of default female avatar url.
	 * 
	 * @return value of default female avatar url.
	 */
	public String getDefaultFemaleAvatarUrl() {
		return defaultFemaleAvatarUrl;
	}
}