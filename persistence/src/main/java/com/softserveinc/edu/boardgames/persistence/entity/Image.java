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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

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
	 * 
	 */
	private static final long serialVersionUID = 2966070992511907459L;

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
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Image other = (Image) obj;
	    return new EqualsBuilder().append(getId(), other.getId())
	                              .append(getUser(), other.getUser())
	                              .append(getImageName(), other.getImageName())
	                              .append(getImageLocation(), other.getImageLocation())
	                              .isEquals();
	}
	
	@Override
	public int hashCode() {
		 return new HashCodeBuilder().append(getId())
				 .append(getUser())
                 .append(getImageName())
                 .append(getImageLocation())
                 .toHashCode();
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this)
        		.append("id", getId())
                .append("name", getUser())
                .append("country", getImageName())
                .append("country", getImageLocation())
                .toString();
    }
}