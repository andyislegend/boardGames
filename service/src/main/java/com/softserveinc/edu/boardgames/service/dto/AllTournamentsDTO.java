package com.softserveinc.edu.boardgames.service.dto;

import sun.util.locale.InternalLocaleBuilder;

import java.util.List;

/**
 * Created by Dora on 18.04.2016.
 */
public class AllTournamentsDTO {

    private Long id;
    private String name;
    private String username;
    private String country;
    private String city;
    private String street;
    private Integer houseNumber;
    private Integer roomNumber;
    private Double requiredRating;

    public AllTournamentsDTO() {
    }

    public AllTournamentsDTO(Long id, String name,
                             String username, String country,
                             String city, String street,
                             Integer houseNumber, Integer roomNumber,
                             Double requiredRating) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.roomNumber = roomNumber;
        this.requiredRating = requiredRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
