package com.softserveinc.edu.boardgames.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * This class is a reflection between 2 users
 * 
 * @author Vasyl Bervetskyy
 * 
 */
@Entity
@Table(name="friends")
public class Friend {
	
	/**
	 * Describes the friendship  id. Unique value. Primary Key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/**
	 * Describes connection to the User entity
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_one", referencedColumnName = "id")
	private User userOne;
	
	/**
	 * Describes connection to the User entity
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_one", referencedColumnName = "id")
	private User userTwo;
	
	/**
	 * Describes status of friendship
	 */
	@Enumerated(EnumType.STRING)
	private Status status;
	
	/**
	 * Describes url where is ava
	 */
	@Column(name="pathToAva")
	private String pathToAva;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUserOne() {
		return userOne;
	}

	public void setUserOne(User userOne) {
		this.userOne = userOne;
	}

	public User getUserTwo() {
		return userTwo;
	}

	public void setUserTwo(User userTwo) {
		this.userTwo = userTwo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getPathToAva() {
		return pathToAva;
	}

	public void setPathToAva(String pathToAva) {
		this.pathToAva = pathToAva;
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + ", userOne=" + userOne + ", userTwo=" + userTwo + ", status=" + status
				+ ", pathToAva=" + pathToAva + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((pathToAva == null) ? 0 : pathToAva.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userOne == null) ? 0 : userOne.hashCode());
		result = prime * result + ((userTwo == null) ? 0 : userTwo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Friend other = (Friend) obj;
		if (id != other.id)
			return false;
		if (pathToAva == null) {
			if (other.pathToAva != null)
				return false;
		} else if (!pathToAva.equals(other.pathToAva))
			return false;
		if (status != other.status)
			return false;
		if (userOne == null) {
			if (other.userOne != null)
				return false;
		} else if (!userOne.equals(other.userOne))
			return false;
		if (userTwo == null) {
			if (other.userTwo != null)
				return false;
		} else if (!userTwo.equals(other.userTwo))
			return false;
		return true;
	}


}
