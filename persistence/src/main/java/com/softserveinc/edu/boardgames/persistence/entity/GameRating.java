package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This class describes game's ratings of boardGames website.
 * 
 * 
 * @author Andrew Petryk
 */
@Entity
@Table(name = "gameRating")
public class GameRating implements Serializable{

	/**
	 * Describes the game rating id. Unique value. Primary Key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * Describes exact game rating.
	 */
	@Column(name = "rating")
	private Double rating;

	/**
	 * Provide one-to-one mapping to the User entity. So-called
	 * "User has a rating"
	 * 
	 */
//	@OneToOne(fetch = FetchType.LAZY)
//	private Game game;

	/**
	 * Default constructor
	 */
	public GameRating() {
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

//	public Game getGame() {
//		return game;
//	}

//	public void setGame(Game game) {
//		this.game = game;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		return result;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GameRating [id=" + id + ", rating=" + rating + "]";
	}



}
