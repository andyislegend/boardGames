package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.sql.Date;

/**
 * @author Daria Bondar
 */
public class AddTournamentDTO {

    private Long tournamentId;
    private String tournamentName;
    private Double rating;
    private Integer maxParticipants;
    private String gameName;
    private Date date;
    private String country;
    private String city;
    private String addition;


    public AddTournamentDTO() {
    }

    public AddTournamentDTO(Long tournamentId,String tournamentName, Double rating, Integer maxParticipants,
                            String gameName, Date date, String country, String city, String addition) {
        this.tournamentId=tournamentId;
        this.tournamentName = tournamentName;
        this.rating = rating;
        this.maxParticipants = maxParticipants;
        this.gameName = gameName;
        this.date = date;
        this.country = country;
        this.city = city;
        this.addition = addition;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }
}
