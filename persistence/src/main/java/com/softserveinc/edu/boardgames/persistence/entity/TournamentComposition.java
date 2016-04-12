package com.softserveinc.edu.boardgames.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Daria Bondar
 * @since 12.04.2016
 */
public class TournamentComposition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Tournament.class)
    @JoinColumn(name = "tournamentId", referencedColumnName = "id")
    private Tournament tournament;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "userGuestId",referencedColumnName = "id")
    private User userGuest;

    public TournamentComposition() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public User getUserGuest() {
        return userGuest;
    }

    public void setUserGuest(User userGuest) {
        this.userGuest = userGuest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TournamentComposition that = (TournamentComposition) o;

        if (!tournament.equals(that.tournament)) return false;
        if (!userGuest.equals(that.userGuest)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tournament.hashCode();
        result = 31 * result + userGuest.hashCode();
        return result;
    }
}
