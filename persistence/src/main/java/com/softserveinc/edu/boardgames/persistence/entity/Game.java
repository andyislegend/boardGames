package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;
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
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class represents data model to game entity
 * Contains data about all kind of games available in system
 * Has relationships with entities Category, GameUser, GameRating
 * @author Taras Varvariuk
 */
@Entity
@Table(name = "game")
public class Game implements Serializable{

	private static final long serialVersionUID = 6137099479297502093L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)       
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Category.class, cascade={CascadeType.ALL})
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	private Category category;
		
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="game", fetch=FetchType.LAZY)
	private Set<GameUser> userGames;
	
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="game", fetch=FetchType.LAZY)
	private Set<GameRating> gameRatings;
	
	public Game(){}
	
	public Game(String name,Category category) {
		this.name = name;
		this.category = category;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<GameUser> getUserGames() {
		return userGames;
	}

	public void setUserGames(Set<GameUser> userGames) {
		this.userGames = userGames;
	}
	
	public Set<GameRating> getGameRatings() {
		return gameRatings;
	}

	public void setGameRatings(Set<GameRating> gameRatings) {
		this.gameRatings = gameRatings;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Game other = (Game) obj;
		return new EqualsBuilder().append(this.getId(), other.getId())
								.append(this.getName(), other.getName())
								.append(this.getCategory(), other.getCategory())
								.append(this.getUserGames(), other.getUserGames()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId())
									.append(this.getName())
									.append(this.getCategory())
									.append(this.getUserGames())
									.toHashCode();
	}
	

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.getId())
				.append("name", this.getName())
				.append("category", this.getCategory())
				.append("userGames", this.getUserGames())
				.toString();
	}
}