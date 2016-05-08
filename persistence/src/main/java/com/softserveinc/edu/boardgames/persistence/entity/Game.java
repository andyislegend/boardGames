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
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.softserveinc.edu.boardgames.persistence.enumeration.GameRating;

/**
 * This class represents data model to game entity
 * Contains data about all kind of games available in system
 * Has ManyToOne relationship to Category,
 * OneToMany relationship to GameUser,
 * OneToOne relationship to GameRating
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
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "rules")
	private String rules;
	
	@Column(name = "ratings")
	private Integer rating;
	
	@Column(name = "minPlayers")
	private Integer minPlayers;
	
	@Column(name = "maxPlayers")
	private Integer maxPlayers;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Category.class, cascade={CascadeType.ALL})
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	private Category category;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="game", cascade={CascadeType.ALL})
    private Set<Event> events;
		
	@OneToMany(cascade={CascadeType.ALL},mappedBy="game", fetch=FetchType.LAZY)
	private Set<GameUser> userGames;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "game")
	@JsonBackReference
    private Set<Tournament> tournaments;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL },mappedBy = "game")
    private Set<GameRatingNumeric> gameRatingNumeric;
	
	public Game(){}
	
	public Game(String name, String description, Integer minPlayers, 
			Integer maxPlayers, Category category) {
		this.name = name;
		this.description = description;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.category = category;
	}
	
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Game(String name, String description, Integer minPlayers, 
			Integer maxPlayers, Category category, GameRating gameRating) {
		this.name = name;
		this.description = description;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.category = category;
	}

	public Set<GameRatingNumeric> getGameRatingNumeric() {
		return gameRatingNumeric;
	}

	public void setGameRatingNumeric(Set<GameRatingNumeric> gameRatingNumeric) {
		this.gameRatingNumeric = gameRatingNumeric;
	}

	public Set<GameUser> getUserGames() {
		return userGames;
	}

	public void setUserGames(Set<GameUser> userGames) {
		this.userGames = userGames;
	}
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(Integer minPlayers) {
		this.minPlayers = minPlayers;
	}

	public Integer getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(Integer maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
    
    public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Tournament> getTournaments() {
		return tournaments;
	}

	public void setTournaments(Set<Tournament> tournaments) {
		this.tournaments = tournaments;
	}

	public void setId(Integer id) {
		this.id = id;
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