package com.softserveinc.edu.boardgames.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * This class is status of friend
 * 
 * @author Vasyl Bervetskyy
 * 
 */
@Entity
@Table(name="status")
public class Status{
	
	/**
	 * Describes the status id. Unique value. Primary Key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/**
	 * Describes status of friendship
	 */
	@Column(name="statusOfFriend")
	private String statusOfFriend;
	
	public Status(long id, String statusOfFriend) {
		super();
		this.id = id;
		this.statusOfFriend = statusOfFriend;
	}
	
	public Status() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatusOfFriend() {
		return statusOfFriend;
	}

	public void setStatusOfFriend(String statusOfFriend) {
		this.statusOfFriend = statusOfFriend;
	}
}
