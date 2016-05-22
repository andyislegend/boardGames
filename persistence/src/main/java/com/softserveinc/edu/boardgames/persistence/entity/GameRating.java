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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * This entity represents info about global games ratings
 * Contains Many-To-One relationship with Game
 * And Many-To-One relationship with User
 * @author TARAZIDZE
 */
@Entity
@Table(name = "gameRatingNumeric", uniqueConstraints=@UniqueConstraint(columnNames="gameId"))
public class GameRating implements Serializable{
	
	private static final long serialVersionUID = 5911151982701538423L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)       
	private Integer id;
	
	@Column(name = "rating")
	private Double rating = 0.0;
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name="gameId", nullable=false)
	private Game game;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class, 
			cascade={ CascadeType.MERGE, CascadeType.REFRESH })
	private User user;

	public GameRating() {}
	
	public GameRating(Integer id, Double rating, Game game, User user) {
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

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
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
