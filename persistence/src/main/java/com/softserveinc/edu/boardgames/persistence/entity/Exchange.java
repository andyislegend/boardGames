package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "exchnge", uniqueConstraints=@UniqueConstraint(columnNames="gameUserId"))
public class Exchange implements Serializable{

	private static final long serialVersionUID = -4102098517901377047L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "userApplierId", columnDefinition="int(11) default '0'")
	private Integer userApplierId;
	
	@Column(name = "dateOfReturn")
    private Date dateOfReturn = Calendar.getInstance().getTime();
	
	@Column(name = "message")
	private String message = "no message";
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private User user;
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name="gameUserId", nullable=false)
	private GameUser gameUser;

	public Exchange() {}
	
	public Exchange(Integer id, Integer userApplierId, Date dateOfReturn, 
			String message, User user, GameUser gameUser) {
		super();
		this.id = id;
		this.userApplierId = userApplierId;
		this.dateOfReturn = dateOfReturn;
		this.message = message;
		this.user = user;
		this.gameUser = gameUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserApplierId() {
		return userApplierId;
	}

	public void setUserApplierId(Integer userApplierId) {
		this.userApplierId = userApplierId;
	}

	public Date getDateOfReturn() {
		return dateOfReturn;
	}

	public void setDateOfReturn(Date dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GameUser getGameUser() {
		return gameUser;
	}

	public void setGameUser(GameUser gameUser) {
		this.gameUser = gameUser;
	}
	
	@Override
	public boolean equals(Object obj) {	
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
