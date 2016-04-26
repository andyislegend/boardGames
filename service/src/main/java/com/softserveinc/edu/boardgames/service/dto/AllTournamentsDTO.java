package com.softserveinc.edu.boardgames.service.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;
import sun.util.locale.InternalLocaleBuilder;

import java.util.List;

/**
 * Created by Dora on 18.04.2016.
 */
public class AllTournamentsDTO {

    private Long tournamentId;
    private String tournamentName;
    private String username;
    private String country;
    private String city;
    private String street;
    private Integer houseNumber;
    private Integer roomNumber;
    private Double requiredRating;
    private Integer maxParticipants;
    private String date;
    private List<String> userGuests;

    public AllTournamentsDTO() {
    }

    public AllTournamentsDTO(Long tournamentId, String tournamentName,
                             String username, String country,
                             String city, String street,
                             Integer houseNumber, Integer roomNumber,
                             Double requiredRating, String date,
                             List<String> userGuests,Integer maxParticipants) {
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.username = username;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.roomNumber = roomNumber;
        this.requiredRating = requiredRating;
        this.date = date;
        this.userGuests = userGuests;
        this.maxParticipants=maxParticipants;
    }

    public AllTournamentsDTO(Long tournamentId, String tournamentName,
                             String username, Double requiredRating, String date,
                             List<String> userGuests,Integer maxParticipants) {
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.username = username;
        this.requiredRating = requiredRating;
        this.date = date;
        this.userGuests = userGuests;
        this.maxParticipants=maxParticipants;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getUserGuests() {
        return userGuests;
    }

    public void setUserGuests(List<String> userGuests) {
        this.userGuests = userGuests;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }
}
