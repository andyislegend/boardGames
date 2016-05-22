package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.Date;
import java.util.Set;

import com.softserveinc.edu.boardgames.persistence.entity.User;

/**
 * @author Volodymyr Krokhmaliuk
 */
public class TournamentsDTO {

    private Integer tournamentId;
    private String tournamentName;
    private Integer countOfParticipants;
    private Integer userCreatorId;
    private String userCreatorName;
    private Date dateOfTournament;
    private Set<User> users;
    private String country;
    private String city;

    public TournamentsDTO() {
    }

    public TournamentsDTO(Integer tournamentId, String tournamentName, Integer countOfParticipants, Integer userCreatorId,
			String userCreatorName, Date date) {
		this.tournamentId = tournamentId;
		this.tournamentName = tournamentName;
		this.countOfParticipants = countOfParticipants;
		this.userCreatorId = userCreatorId;
		this.userCreatorName = userCreatorName;
		this.dateOfTournament = date;
	}
    
    public TournamentsDTO(Integer tournamentId, String tournamentName) {
		this.tournamentId = tournamentId;
		this.tournamentName = tournamentName;
	}

	public Integer getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(Integer tournamentId) {
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

	public Integer getUserCreatorId() {
		return userCreatorId;
	}

	public void setUserCreatorId(Integer userCreatorId) {
		this.userCreatorId = userCreatorId;
	}

	public String getUserCreatorName() {
		return userCreatorName;
	}

	public void setUserCreatorName(String userCreatorName) {
		this.userCreatorName = userCreatorName;
	}

	public Date getDateOfTournament() {
		return dateOfTournament;
	}

	public void setDateOfTournament(Date dateOfTournament) {
		this.dateOfTournament = dateOfTournament;
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