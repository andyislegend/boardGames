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
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	@Column(name = "minPlayers")
	private Integer minPlayers;
	
	@Column(name = "maxPlayers")
	private Integer maxPlayers;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false, targetEntity=Category.class, cascade={CascadeType.ALL})
	@JoinColumn(name="categoryId")
	private Category category;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="game", fetch=FetchType.LAZY)
	private Set<GameUser> userGames;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "game", cascade = CascadeType.ALL)
	private GameRating gameRating;

	public Game(){}
	
	public Game(String name, String description, Integer minPlayers, 
			Integer maxPlayers, Category category, GameRating gameRating) {
		super();
		this.name = name;
		this.description = description;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.category = category;
		this.gameRating = gameRating;
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