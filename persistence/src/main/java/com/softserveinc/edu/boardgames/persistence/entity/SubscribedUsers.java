package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "subscribed_users")
public class SubscribedUsers implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2946754119029751023L;

	@Id
    @ManyToOne
    @JoinColumn(name = "event_id")
	private Event event;
	
	@Id
    @ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	
	@Column
	private boolean isNew = true;

	public SubscribedUsers() {
		
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubscribedUsers other = (SubscribedUsers) obj;
		return new EqualsBuilder()
				.append(getUser(), other.getUser())
				.append(getEvent(), other.getEvent())
				.append(isNew(), other.isNew())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(getUser())
				.append(getEvent())
				.append(isNew())
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("event", getEvent())
				.append("user", getUser())
				.append("new", isNew())
				.toString();
	}
	
	
	
}
