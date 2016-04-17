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
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

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

	/**
	 * unique value, primary key
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)       
	private Integer id;
	
	/**
	 * the name of the game
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * description of game
	 * may contain general rules
	 * and game class info
	 */
	@Column(name = "description")
	private String description;
	
	/**
	 * minimum number of players
	 */
	@Column(name = "minPlayers")
	private Integer minPlayers;
	
	/**
	 * maximum number of players in the game
	 */
	@Column(name = "maxPlayers")
	private Integer maxPlayers;
	
	/**
	 * foreign key
	 * ManyToOne relationship to Category 
	 */
	@ManyToOne(fetch=FetchType.LAZY, optional=false, targetEntity=Category.class, cascade={CascadeType.ALL})
	@JoinColumn(name="categoryId")
	private Category category;
	
	/**
	 * by Anna for Events connections 
	 */
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="game")
    private Set<Event> events;
	
	/**
	 * foreign key
	 * OneToMany relationship to GameUser
	 * set of games of particular user that belongs to this kind of game 
	 */
	@OneToMany(cascade={CascadeType.ALL},mappedBy="game", fetch=FetchType.LAZY)
	private Set<GameUser> userGames;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "game")
    private List<Tournament> tournaments;
	
	@NotEmpty
	@Column(name = "rating", nullable = false)
	private GameRating gameRating = GameRating.NOT_RATED;

	public Game(){}
	
	public Game(String name, String description, Integer minPlayers, 
			Integer maxPlayers, Category category) {
		
		this.name = name;
		this.description = description;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.category = category;
		
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
	
	public GameRating getGameRating() {
		return gameRating;
	}

	public void setGameRating(GameRating gameRating) {
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

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }
    
    @Override
    public String toString() {
        return "Game [id=" + id + ", category=" + category + ", maxPlayers=" + maxPlayers +
        		", minPlayers=" + minPlayers +
        		", description=" + description + ", name=" + name + ", gameRating=" + gameRating + "]";
    }
}
