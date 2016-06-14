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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Represents every exchange operation in system
 * has unique constraint on field gameUserId
 * also has relationships with user and set of game propositions
 * @author Varvariuk Taras
 *
 */
@Entity
@Table(name = "exchnge", uniqueConstraints=@UniqueConstraint(columnNames="gameUserId"))
public class Exchange implements Serializable{

	private static final long serialVersionUID = -4102098517901377047L;
	
	/**
	 * Unique value, primary key
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * contains identifier for user who applies for game
	 */
	@Column(name = "userApplierId", columnDefinition="int(11) default '0'")
	private Integer userApplierId;
	
	/**
	 * period of sharing, default value {14}
	 */
	@Column(name = "period")
    private Integer period = 14;
	
	/**
	 * message, applier attaches to request
	 */
	@Column(name = "message")
	private String message = "no message";
	
	/**
	 * when owner confirms request applyingDate is set, can be null
	 */
	@Column(name = "applyingDate")
	private Date applyingDate;
	
	/**
	 * game owner
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private User user;
	
	/**
	 * represents instance of game, only one game can be available at a time
	 */
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name="gameUserId", nullable=false)
	private GameUser gameUser;
	
	/**
	 * represents data about userApplier and games he want to propose for this exchange
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy="exchange", cascade={CascadeType.ALL})
    private Set<GameProposition> gamePropositions;

	public Exchange() {}
	
	public Exchange(Integer id, Integer userApplierId, Integer dateOfReturn, 
			String message, User user, GameUser gameUser) {
		super();
		this.id = id;
		this.userApplierId = userApplierId;
		this.period = dateOfReturn;
		this.message = message;
		this.user = user;
		this.gameUser = gameUser;
	}
	
	public Exchange(Integer userApplierId, Integer dateOfReturn, 
			String message, User user, GameUser gameUser) {
		super();
		this.userApplierId = userApplierId;
		this.period = dateOfReturn;
		this.message = message;
		this.user = user;
		this.gameUser = gameUser;
	}
	
	public Exchange(Integer dateOfReturn, 
			String message, User user, GameUser gameUser) {
		super();
		this.period = dateOfReturn;
		this.message = message;
		this.user = user;
		this.gameUser = gameUser;
	}

	public Date getApplyingDate() {
		return applyingDate;
	}

	public void setApplyingDate(Date applyingDate) {
		this.applyingDate = applyingDate;
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

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
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
	
	public Set<GameProposition> getGamePropositions() {
		return gamePropositions;
	}

	public void setGamePropositions(Set<GameProposition> gamePropositions) {
		this.gamePropositions = gamePropositions;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Exchange other = (Exchange) obj;
		return new EqualsBuilder().append(this.getApplyingDate(), other.getApplyingDate())
								.append(this.getGameUser(), other.getGameUser())
								.append(this.getUser(), other.getUser())
								.append(this.getMessage(), other.getMessage())
								.append(this.getPeriod(), other.getPeriod())
								.append(this.getUserApplierId(), other.getUserApplierId())
								.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getApplyingDate())
									.append(this.getGameUser())
									.append(this.getUser())
									.append(this.getPeriod())
									.append(this.getUserApplierId())
									.append(this.getMessage())
									.toHashCode();
	}
	

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.getId())
				.append("applying date", this.getApplyingDate())
				.append("game_user_id", this.getGameUser().getId())
				.append("user_id", this.getUser().getId())
				.append("period", this.getPeriod())
				.append("user_applier_id", this.getUserApplierId())
				.append("message", this.getMessage())
				.toString();
	}
}
