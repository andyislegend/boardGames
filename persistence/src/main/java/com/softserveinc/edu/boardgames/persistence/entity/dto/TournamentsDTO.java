package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.util.Date;
import java.util.Set;

import com.softserveinc.edu.boardgames.persistence.entity.City;
import com.softserveinc.edu.boardgames.persistence.entity.Country;
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
    private Country country;
    private City city;
    private boolean isTableGenerated;

    public TournamentsDTO() {
    }

    public TournamentsDTO(Integer tournamentId, String tournamentName, Integer countOfParticipants, Integer userCreatorId,
			String userCreatorName, Date date, boolean isTableGenerated) {
		this.tournamentId = tournamentId;
		this.tournamentName = tournamentName;
		this.countOfParticipants = countOfParticipants;
		this.userCreatorId = userCreatorId;
		this.userCreatorName = userCreatorName;
		this.dateOfTournament = date;
		this.isTableGenerated = isTableGenerated;
	}
    
    public TournamentsDTO(Date date) {
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