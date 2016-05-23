package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This entity is set for storing information about Events
 * 
 * @author Andrii Petryk
 */
@Entity
@Table(name = "events")
public class Event implements Serializable {

	private static final long serialVersionUID = -8068483407568107770L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@Column
	private String description;

	@Column
	private Date date;

	@Column
	private String place;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	private Country country;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	private City city;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	private Game game;

	public Event() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return new EqualsBuilder()
				.append(getId(), other.getId())
				.append(getName(), other.getName())
				.append(getDescription(), other.getDescription())
				.append(getPlace(), other.getPlace())
				.append(getDate(), other.getDate())
				.append(getCity(), other.getCity())
				.append(getUser(), other.getUser())
				.append(getCountry(), other.getCountry())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(getId())
				.append(getUser())
				.append(getName())
				.append(getDescription())
				.append(getPlace())
				.append(getDate())
				.append(getCountry())
				.append(getCity())
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", getId())
				.append("name", getName())
				.append("user", getUser())
				.append("date", getDate())
				.append("description", getDescription())
				.append("place", getPlace())
				.append("city", getCity())
				.append("country", getCountry())
				.toString();
	}
}
