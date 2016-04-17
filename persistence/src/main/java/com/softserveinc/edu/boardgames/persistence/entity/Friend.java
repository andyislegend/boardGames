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
    @JoinColumn(name = "user_two", referencedColumnName = "id")
	private User userTwo;
	
	
	
	/**
	 * Describes status of friendship
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Status.class)
    @JoinColumn(name = "status", referencedColumnName = "id")
	private Status status;
	
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



}
