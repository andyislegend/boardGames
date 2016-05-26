package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;

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
/**
 * This entity represents info about global games ratings
 * has relationships with Game and User,
 * User can rate multiple games
 * and Game can be rated by multiple users
 * @author Taras Vrvariuk
 */
@Entity
@Table(name = "gameRating")
public class GameRating implements Serializable{
	
	private static final long serialVersionUID = 5911151982701538423L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)       
	private Integer id;
	
	@Column(name = "rating")
	private Integer rating = 0;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Game.class, cascade=CascadeType.MERGE)
	@JoinColumn(name = "gameId", referencedColumnName = "id")
	private Game game;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class, 
			cascade={ CascadeType.MERGE, CascadeType.REFRESH })
	private User user;

	public GameRating() {}
	
	public GameRating(Integer id, Integer rating, Game game, User user) {
		super();
		this.id = id;
		this.rating = rating;
		this.game = game;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    GameRating other = (GameRating) obj;
		return new EqualsBuilder().append(this.getId(), other.getId())
								.append(this.getRating(), other.getRating())
								.append(this.getGame(), other.getGame())
								.append(this.getUser(), other.getUser())
								.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId())
									.append(this.getRating())
									.append(this.getGame())
									.append(this.getUser())
									.toHashCode();
	}
	

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.getId())
				.append("rating", this.getRating())
				.append("game", this.getGame())
				.append("user", this.getUser())
				.toString();
	}
}
