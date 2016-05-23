package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

	@Column(nullable = false)
	private String name;

	@Column(length = 500)
	private String description;

	@Column
	private Date date;

	@Column
	private String location;

	@Column
	private boolean isNew = true;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE }, mappedBy = "events")
	private Set<User> users;

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public Set<User> getUser() {
		return users;
	}

	public void setUser(Set<User> user) {
		this.users = user;
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
				.append(getLocation(), other.getLocation())
				.append(getDate(), other.getDate())
				.append(isNew(), other.isNew())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(getId())
				.append(getName())
				.append(getDescription())
				.append(getLocation())
				.append(getDate())
				.append(isNew())
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", getId())
				.append("name", getName())
				.append("date", getDate())
				.append("description", getDescription())
				.append("location", getLocation())
				.toString();
	}
}
