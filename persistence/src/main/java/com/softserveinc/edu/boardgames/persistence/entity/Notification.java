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

import com.fasterxml.jackson.annotation.JsonBackReference;
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
	
	@Column(name = "status_of_reading", columnDefinition="tinyint(1) default 0")
	private boolean statusOfReading;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=User.class, cascade={CascadeType.MERGE})
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=User.class)
	@JoinColumn(name = "user_sender", referencedColumnName = "id")
	private User userSender;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional=true)
	@JoinColumn(name="gameUserId", referencedColumnName = "id")
	private GameUser gameUser;
	
	@Column(name="date")
	private Date date = new Date();
	
	public Notification() {}

	public Notification(Integer id, String type, String status, String message,
		 User user, Date date) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
		this.message = message;
		this.user = user;
		this.date = date;
	}
	
	
	public Notification(Integer id, String type, String status, String message, User user,
			User userSender, GameUser gameUser, Date date) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
		this.message = message;
		this.user = user;
		this.userSender = userSender;
		this.gameUser = gameUser;
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
	public GameUser getGameUser() {
		return gameUser;
	}
	public void setGameUser(GameUser gameUser) {
		this.gameUser = gameUser;
	}

	public User getUserSender() {
		return userSender;
	}

	public void setUserSender(User userSender) {
		this.userSender = userSender;
	}

	public boolean isStatusOfReading() {
		return statusOfReading;
	}

	public void setStatusOfReading(boolean statusOfReading) {
		this.statusOfReading = statusOfReading;
	}
	
}
