package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.Date;
import java.util.Set;

import com.softserveinc.edu.boardgames.persistence.entity.User;

/**
 * @author Volodymyr Krokhmaliuk
 */
public class TournamentsDTO {

    private Long tournamentId;
    private String tournamentName;
    private Integer countOfParticipants;
    private Integer gameUserId;
    private Date date;
    private Set<User> users;
    private String country;
    private String city;

    public TournamentsDTO() {
    }

    public TournamentsDTO(String tournamentName, Integer countOfParticipants,
                            Integer gameUserId, Date date, String country, String city) {
        this.tournamentName = tournamentName;
        this.countOfParticipants = countOfParticipants;
        this.gameUserId = gameUserId;
        this.date = date;
        this.country = country;
        this.city = city;    
    }
    
	public TournamentsDTO(Long tournamentId, String tournamentName, Integer countOfParticipants, Integer gameUserId,
			Date date, Set<User> users, String country, String city) {
		this.tournamentId = tournamentId;
		this.tournamentName = tournamentName;
		this.countOfParticipants = countOfParticipants;
		this.gameUserId = gameUserId;
		this.date = date;
		this.users = users;
		this.country = country;
		this.city = city;
	}

	public Long getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}

	public String getTournamentName() {
		return tournamentName;
	}

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	public Integer getCountOfParticipants() {
		return countOfParticipants;
	}

	public void setCountOfParticipants(Integer countOfParticipants) {
		this.countOfParticipants = countOfParticipants;
	}

	public Integer getGameUserId() {
		return gameUserId;
	}

	public void setGameUserId(Integer gameUserId) {
		this.gameUserId = gameUserId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}