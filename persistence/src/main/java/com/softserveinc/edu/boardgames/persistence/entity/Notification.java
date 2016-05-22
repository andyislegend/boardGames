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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.softserveinc.edu.boardgames.persistence.enumeration.NotificationStatus;

@Entity
@Table(name = "notification")
public class Notification implements Serializable {

	private static final long serialVersionUID = -6003000356073579546L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "status")
	private String status = NotificationStatus.UNCHECKED.name();
	
	@Column(name = "message")
	private String message = "no message";
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class, cascade={CascadeType.MERGE})
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
	
	@Column(name="date")
	private Date date;
	
	public Notification() {}

	public Notification(Integer id, String type, String status, String message, User user, Date date) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
		this.message = message;
		this.user = user;
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
