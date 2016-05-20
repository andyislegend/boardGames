package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class is used to verify provided email by user during registration and
 * to user's confirm registration
 * 
 * @author Andrii Petryk
 * 
 *
 * 
 */
@Entity
public class VerificationToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4058412545610441245L;

	/**
	 * @param EXPIRATION
	 *            is used to calculate the time and date of verification token
	 */
	private static final int EXPIRATION = 60;

	/**
	 * Entity's id. Unique value
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * String token, used to form confirmation link
	 */
	@Column
	private String token;

	/**
	 * Date and time of tokens expiration. Can't be used after it.
	 */
	@Column
	private Date expiryDate;

	/**
	 * Reference to User entity
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	public VerificationToken() {

	}

	public VerificationToken(final String token) {

		this.token = token;
		this.expiryDate = calculateExpiryDate(EXPIRATION);

	}

	public VerificationToken(final String token, final User user) {

		this.token = token;
		this.user = user;
		this.expiryDate = calculateExpiryDate(EXPIRATION);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).append(getToken()).append(getUser()).append(getExpiryDate())
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerificationToken other = (VerificationToken) obj;
		return new EqualsBuilder().append(getId(), other.getId()).append(getToken(), other.getToken())
				.append(getUser(), other.getUser()).append(getExpiryDate(), other.getExpiryDate()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).append("token", getToken())
				.append("expire", getExpiryDate()).toString();
	}

	/**
	 * 
	 * @param expiryTimeInMinutes
	 * @return Date of tokens expiring
	 */
	private Date calculateExpiryDate(final int expiryTimeInMinutes) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}

}
