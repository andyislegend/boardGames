package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.softserveinc.edu.boardgames.persistence.enumeration.GameRating;

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

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "edition")
	private String edition;

	@Column(name = "yearOfProduction")
	private Integer yearOfProduction;

	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Game.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = "gameId")
	private Game game;

	@NotEmpty
	@Column(name = "gameUserRating", nullable = false)
	private String gameUserRating = GameRating.NOT_RATED.name();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "user", joinColumns = {
			@JoinColumn(name = "userId", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "userGames", nullable = false, updatable = false) })
	private Set<User> users = new HashSet<>();

	public GameUser() {
	};

	public GameUser(String edition, Integer yearOfProduction, Game game, User user) {
		super();
		this.edition = edition;
		this.yearOfProduction = yearOfProduction;
		this.game = game;
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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
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
		if (users != other.users) {
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
		return "GameUser [id=" + id + ", game=" + game + ", yearOfProduction=" + yearOfProduction + ", edition="
				+ edition + "]";
	}
}
