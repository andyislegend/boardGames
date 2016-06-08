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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This entity contains data model describing games of particular user Has
 * ManyToOne relationship to game ManyToMany relationship to user
 * 
 * @authors Taras Varvariuk,Volodymyr Krokhmaliuk
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
	
	@Column(name = "description", length=2000)
	private String description;
	
	@Column(name = "rules", length=2000)
	private String rules;
	
	@Column(name = "minPlayers")
	private Integer minPlayers;
	
	@Column(name = "maxPlayers")
	private Integer maxPlayers;
	
	@Column(name = "countOfComments")
	private Integer countOfComments;
	
	@Column(name = "status", columnDefinition="varchar(20) default 'PRIVATE'")
	private String status;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.REMOVE})
	private Game game;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	private User user;
	
	@OneToOne(mappedBy="gameUser")
	private Exchange exchange;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="gameUser", cascade={CascadeType.ALL})
    private Set<GameProposition> gamePropositions;
	
	public GameUser() {
		
	}
	
	public GameUser(String edition, Integer yearOfProduction, Game game, User user, String status) {
		super();
		this.edition = edition;
		this.yearOfProduction = yearOfProduction;
		this.countOfComments = 0;
		this.game = game;
		this.user = user;
		this.status = status;
	}
	
	public GameUser(String edition, Integer yearOfProduction,Integer countOfComments, Game game, User user) {
		super();
		this.edition = edition;
		this.yearOfProduction = yearOfProduction;
		this.countOfComments = countOfComments;
		this.game = game;
		this.user = user;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
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

	public Integer getCountOfComments() {
		return countOfComments;
	}

	public void setCountOfComments(Integer countOfComments) {
		this.countOfComments = countOfComments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Exchange getExchange() {
		return exchange;
	}

	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	public Set<GameProposition> getGamePropositions() {
		return gamePropositions;
	}

	public void setGamePropositions(Set<GameProposition> gamePropositions) {
		this.gamePropositions = gamePropositions;
	}
}