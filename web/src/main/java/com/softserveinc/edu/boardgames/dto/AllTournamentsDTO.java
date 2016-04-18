package com.softserveinc.edu.boardgames.dto;

import java.util.List;

/**
 * Created by Dora on 18.04.2016.
 */
public class AllTournamentsDTO {

    private String tournamentName;
    private String userName;
    private List<String> userGuests;

    public AllTournamentsDTO() {
    }

    public AllTournamentsDTO(String tournamentName, String userName, List<String> userGuests) {
        this.tournamentName = tournamentName;
        this.userName = userName;
        this.userGuests = userGuests;
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
}
