package com.softserveinc.edu.boardgames.service.dto;

import sun.util.locale.InternalLocaleBuilder;

import java.util.List;

/**
 * Created by Dora on 18.04.2016.
 */
public class AllTournamentsDTO {

    private Long tournamentId;
    private String tournamentName;
    private String userName;
    private String requiredRating;
    private List<String> userGuests;
    private boolean isCanJoin;

    public AllTournamentsDTO() {
    }

    public AllTournamentsDTO(Long tournamentId,
                             String tournamentName,
                             String userName,
                             String requiredRating,
                             List<String> userGuests,boolean isCanJoin) {
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.userName = userName;
        this.requiredRating = requiredRating;
        this.userGuests = userGuests;
        this.isCanJoin=isCanJoin;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getUserGuests() {
        return userGuests;
    }

    public void setUserGuests(List<String> userGuests) {
        this.userGuests = userGuests;
    }

    public String getRequiredRating() {
        return requiredRating;
    }

    public void setRequiredRating(String requiredRating) {
        this.requiredRating = requiredRating;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public boolean isCanJoin() {
        return isCanJoin;
    }

    public void setIsCanJoin(boolean isCanJoin) {
        this.isCanJoin = isCanJoin;
    }
}
