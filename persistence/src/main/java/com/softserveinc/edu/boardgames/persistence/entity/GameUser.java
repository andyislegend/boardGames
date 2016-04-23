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

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * This entity contains data model describing games of particular user Has
 * ManyToOne relationship to game ManyToMany relationship to user
 * 
 * @author Taras Varvariuk
 *
 */
@Entity
@Table(name = "gameUser")
public class GameUser implements Serializable {

	private static final long serialVersionUID = 9107019551046622111L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "edition")
	private String edition;

	@Column(name = "yearOfProduction")
	private Integer yearOfProduction;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Game game;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private User user;

	public GameUser() {
	};

	public GameUser(String edition, Integer yearOfProduction, Game game, User user) {
		super();
		this.edition = edition;
		this.yearOfProduction = yearOfProduction;
		this.game = game;
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	
	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public Integer getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(Integer yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		GameUser other = (GameUser) obj;
		if (id != other.id) {
			return false;
		}
		if (yearOfProduction != other.yearOfProduction) {
			return false;
		}
		if (edition != other.edition) {
			return false;
		}
		if (game != other.game) {
			return false;
		}
		if (user != other.user) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + 1;
		return result;
	}

	@Override
	public String toString() {
		return "GameUser [id=" + id + ", game=" + game + 
				", yearOfProduction=" + yearOfProduction + ", edition="
				+ edition + "]";
	}
}
