package com.softserveinc.edu.boardgames.persistence.entity.dto;


import java.util.Date;
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
    private String addition;
    private Double requiredRating;
    private Integer maxParticipants;
    private List<String> userGuests;
    private Integer countParticipants;
    private Date dateUtil;
    private Boolean isCanJoin;

    public AllTournamentsDTO() {
    }


    public AllTournamentsDTO(Long tournamentId, String tournamentName, String username, String country, String city,
                             String addition, Date dateUtil,Double requiredRating, Integer maxParticipants) {
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.username = username;
        this.country = country;
        this.city = city;
        this.addition=addition;
        this.requiredRating = requiredRating;
        this.dateUtil = dateUtil;
        this.maxParticipants=maxParticipants;
    }

    public AllTournamentsDTO(Long tournamentId, String tournamentName, String username,
                             String country, String city, String addition, Date dateUtil, Double requiredRating) {
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.username = username;
        this.country = country;
        this.city = city;
        this.addition = addition;
        this.dateUtil = dateUtil;
        this.requiredRating = requiredRating;
    }

    public AllTournamentsDTO(List<String> userGuests) {
        this.userGuests = userGuests;
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

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public void setDateUtil(Date dateUtil) {
        this.dateUtil = dateUtil;
    }

    public Double getRequiredRating() {
        return requiredRating;
    }

    public void setRequiredRating(Double requiredRating) {
        this.requiredRating = requiredRating;
    }

    public Date getDateUtil() {
        return dateUtil;
    }

    public void setDateSql(Date dateUtil) {
        this.dateUtil = dateUtil;
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

    public Integer getCountParticipants() {
        return countParticipants;
    }

    public void setCountParticipants(Integer countParticipants) {
        this.countParticipants = countParticipants;
    }

    public Boolean getIsCanJoin() {
        return isCanJoin;
    }

    public void setIsCanJoin(Boolean isCanJoin) {
        this.isCanJoin = isCanJoin;
    }
}