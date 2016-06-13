package com.softserveinc.edu.boardgames.persistence.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author Volodymyr Krokhmalyuk
 * 
 * This entity is for storing information about tournament which users want to hold
 */
@Entity
@Table(name = "tournament")
public class Tournament implements Serializable {

    /**
	 * 
	 */

	private static final long serialVersionUID = -4938895533945763751L;
	/**
	 * 
	 */

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Discribes tournament's name
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * User that created this tournament
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private User userCreator;

    /**
     * Kind of game which is tournament organized on
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REMOVE })
    private GameUser game;

    @Column(nullable = false)
    private Integer countOfParticipants;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.ALL })
    private Set<User> users;
    
    @Column(nullable = false)
    private Date dateOfTournament;

    @ManyToOne (fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REMOVE })
    private Country country;
    
    @ManyToOne (fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REMOVE })
    private City city;
    
    @Column(name = "isTableGenerated")
    private boolean isTableGenerated;
    
    public Tournament() {
		
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

	public User getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(User userCreator) {
		this.userCreator = userCreator;
	}

	public GameUser getGame() {
		return game;
	}

	public void setGame(GameUser game) {
		this.game = game;
	}

	public Integer getCountOfParticipants() {
		return countOfParticipants;
	}

	public void setCountOfParticipants(Integer countOfParticipants) {
		this.countOfParticipants = countOfParticipants;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Date getDateOfTournament() {
		return dateOfTournament;
	}

	public void setDateOfTournament(Date dateOfTournament) {
		this.dateOfTournament = dateOfTournament;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public boolean isTableGenerated() {
		return isTableGenerated;
	}

	public void setTableGenerated(boolean isTableGenerated) {
		this.isTableGenerated = isTableGenerated;
	}	
}