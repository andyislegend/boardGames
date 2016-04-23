package com.softserveinc.edu.boardgames.persistence.entity.dto;

/**
 * Created by Dora on 18.04.2016.
 */
public class AllTournamentsDTO {

    private Long tournamentId;
    private String tournamentName;
    private String username;
    private String gameName;
    private String country;
    private String city;
    private String street;
    private Integer houseNumber;
    private Integer roomNumber;
    private Double requiredRating;

    public AllTournamentsDTO() {
    }

    public AllTournamentsDTO(Long tournamentId, String tournamentName, String username, String gameName, String country, String city, String street, Integer houseNumber, Integer roomNumber, Double requiredRating) {
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.username = username;
        this.gameName = gameName;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.roomNumber = roomNumber;
        this.requiredRating = requiredRating;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
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

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getRequiredRating() {
        return requiredRating;
    }

    public void setRequiredRating(Double requiredRating) {
        this.requiredRating = requiredRating;
    }
}
