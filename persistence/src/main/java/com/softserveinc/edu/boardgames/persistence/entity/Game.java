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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

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
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Category.class, cascade={CascadeType.ALL})
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	private Category category;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="game", cascade={CascadeType.ALL})
    private List<Event> events;
		
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="game", fetch=FetchType.LAZY)
	private Set<GameUser> userGames;

	@OneToOne(mappedBy="game")
	private GameRating gameRating;
	
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

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Set<GameUser> getUserGames() {
		return userGames;
	}

	public void setUserGames(Set<GameUser> userGames) {
		this.userGames = userGames;
	}

	public GameRating getGameRating() {
		return gameRating;
	}

	public void setGameRating(GameRating gameRating) {
		this.gameRating = gameRating;
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