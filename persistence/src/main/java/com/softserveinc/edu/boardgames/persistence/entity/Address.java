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
 * This class describes addresses where users of boardGames website live and
 * 		where tournaments are held.
 * 
 * This Address entity has a one to many relationship with the User entity and
 * 		one to many relationship with Tournament entity.
 * 
 * @author Volodymyr Terlyha
 */
@Entity
@Table(name = "address")
public class Address implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2966070992511907458L;

	/**
	 * Describes the address id. Unique value.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * Describes the country where user lives.
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "countryId", referencedColumnName = "id")
	private Country country;

	/**
	 * Describes the city where user lives.
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "cityId", referencedColumnName = "id")
	private City city;

	/**
	 * Describes the post code where user lives.
	 */
	@Column(name = "postCode")
	private Integer postCode;

	/**
	 * Describes the street where user lives.
	 */
	@Column(name = "street")
	private String street;

	/**
	 * Describes the houseNumber where user lives.
	 */
	@Column(name = "houseNumber")
	private Integer houseNumber;

	/**
	 * Describes the roomNumberNumber where user lives.
	 */
	@Column(name = "roomNumber")
	private Integer roomNumber;

	public Address() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Integer getPostCode() {
		return postCode;
	}

	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Address other = (Address) obj;
	    return new EqualsBuilder().append(getId(), other.getId())
	                              .append(getCountry(), other.getCountry())
	                              .append(getCity(), other.getCity())
	                              .append(getPostCode(), other.getPostCode())
	                              .append(getStreet(), other.getStreet())
	                              .append(getHouseNumber(), other.getHouseNumber())
	                              .append(getRoomNumber(), other.getRoomNumber())
	                              .isEquals();
	}
	
	@Override
	public int hashCode() {
		 return new HashCodeBuilder().append(getId())
				 .append(getCountry())
				 .append(getCity())
				 .append(getPostCode())
				 .append(getStreet())
				 .append(getHouseNumber())
				 .append(getRoomNumber())
                 .toHashCode();
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("username", getCountry())
                .append("firstName", getCity())
                .append("lastName", getPostCode())
                .append("gender", getStreet())
                .append("age", getHouseNumber())
                .append("email", getRoomNumber())
                .toString();
    }
}