package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "gameRatingNumeric")
public class GameRatingNumeric implements Serializable{
	
	private static final long serialVersionUID = 5911151982701538423L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)       
	private Integer id;
	
	@Column(name = "rating")
	private Integer rating;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Game.class, 
			cascade={ CascadeType.MERGE, CascadeType.REFRESH })
	private Game game;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class, 
			cascade={ CascadeType.MERGE, CascadeType.REFRESH })
	private User user;

	public GameRatingNumeric() {}
	
	public GameRatingNumeric(Integer id, Integer rating, Game game, User user) {
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
