package com.softserveinc.edu.boardgames.persistence.entity.dto;

import java.sql.Date;

/**
 * Created by Dora on 23.04.2016.
 */
public class AddTournamentDTO {

    private String tournamentName;
    private String gameName;
    private Date date;
    private Double rating;
    private Integer maxParticipants;
    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private String roomNumber;

    public AddTournamentDTO() {
    }

    public AddTournamentDTO(String tournamentName, String gameName,
                            Date date, Double rating, Integer maxParticipants,
                            String country, String city, String street,
                            String houseNumber, String roomNumber) {
        this.tournamentName = tournamentName;
        this.gameName = gameName;
        this.date = date;
        this.rating = rating;
        this.maxParticipants = maxParticipants;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.roomNumber = roomNumber;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
