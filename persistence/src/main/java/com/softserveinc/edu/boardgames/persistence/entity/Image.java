package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class describes images users want to download on website.
 * 
 * This Image entity has a many to one relationship with the User entity.
 * 
 * @author Volodymyr Terlyha
 */
@Entity
@Table(name = "image")
public class Image implements Serializable {

	/**
	 * Describes the image id. Unique value.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * Describes the user who downloads pictures.
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE,
			CascadeType.REFRESH })
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;

	/**
	 * Describes the name of the image user wants to upload.
	 */
	@Column(name = "imageName")
	private String imageName;

	/**
	 * Describes the location of image after user uploads it.
	 */
	@Column(name = "imageLocation")
	private String imageLocation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
}

