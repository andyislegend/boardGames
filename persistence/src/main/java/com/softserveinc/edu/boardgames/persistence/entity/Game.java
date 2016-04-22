package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;
import java.util.List;
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

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
	
	@Column(name = "minPlayers")
	private Integer minPlayers;
	
	@Column(name = "maxPlayers")
	private Integer maxPlayers;
	
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Category.class, cascade={CascadeType.ALL})
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	private Category category;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="game", cascade={CascadeType.ALL})
    private Set<Event> events;
	
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="game", fetch=FetchType.LAZY)
	private Set<GameUser> userGames;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "game")
    private Set<Tournament> tournaments;
	
	@NotEmpty
	@Column(name = "gameRating", nullable=false)
	private String gameRating = GameRating.NOT_RATED.name();

	public Game(){}
	

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
	
	public String getGameRating() {
		return gameRating;
	}

	public void setGameRating(String gameRating) {
		this.gameRating = gameRating;
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
        Game other = (Game) obj;
        if (id != other.id) {
            return false;
        }       
        if (category != other.category) {
            return false;
        }
        if (maxPlayers != other.maxPlayers) {
            return false;
        }
        if (minPlayers != other.minPlayers) {
            return false;
        }
        if (description != other.description) {
            return false;
        }
        if (name != other.name) {
            return false;
        }
        if (gameRating != other.gameRating){
        	return false;
        }
        return true;
    }
    
    public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<GameUser> getUserGames() {
		return userGames;
	}

	public void setUserGames(Set<GameUser> userGames) {
		this.userGames = userGames;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + 1;
        return result;
    }
    
    @Override
    public String toString() {
        return "Game [id=" + id + ", category=" + category + ", maxPlayers=" + maxPlayers +
        		", minPlayers=" + minPlayers +
        		", description=" + description + ", name=" + name + ", gameRating=" + gameRating + "]";
    }
}
