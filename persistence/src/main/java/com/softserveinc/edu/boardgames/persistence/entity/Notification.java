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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.softserveinc.edu.boardgames.persistence.enumeration.NotificationStatus;

/**
 * Notification is any action that informs user
 * about any kinds of activities: events, tournaments, exchanges
 * has relationships with User and GameUser(unrequired)
 * @author Taras Varvariuk, Vasyl Berveckyi
 */
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
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Notification other = (Notification) obj;
		return new EqualsBuilder().append(this.getId(), other.getId())
								.append(this.getGameUser(), other.getGameUser())
								.append(this.getDate(), other.getDate())
								.append(this.getMessage(), other.getMessage())
								.append(this.getStatus(), other.getStatus())
								.append(this.getType(), other.getType())
								.append(this.getUser(), other.getUser())
								.append(this.getUserSender(), other.getGameUser())
								.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId())
									.append(this.getGameUser())
									.append(this.getDate())
									.append(this.getMessage())
									.append(this.getStatus())
									.append(this.getType())
									.append(this.getUser())
									.append(this.getUserSender())
									.toHashCode();
	}
	

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.getId())
				.append("gameUser", this.getGameUser())
				.append("date", this.getDate())
				.append("message", this.getMessage())
				.append("status", this.getStatus())
				.append("type", this.getType())
				.append("user", this.getUser())
				.append("userSender", this.getUserSender())
				.toString();
	}
}
