package com.softserveinc.edu.boardgames.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Daria Bondar
 * @since 12.04.2016
 *
 * This entity is for storing information abot tournament which users want to hold
 */
@Entity
@Table(name = "tournament")
public class Tournament implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Discribes tournament's name
     */
    @Column
    private String name;

    /**
     * User that created this tournament
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "idUserCreator", referencedColumnName = "id")
    private User userCreator;

    /**
     * List of tournament compositions (users) that take part in this tounament
     */
    @OneToMany(fetch = FetchType.LAZY, targetEntity = TournamentComposition.class, cascade = CascadeType.ALL)
    private List<TournamentComposition> tournamentComposition;

    /**
     * Kind of game which is tournament organized on
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Game.class)
    @JoinColumn(name = "gameid", referencedColumnName = "id")
    private Game game;


    public Tournament() {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public User getUserCreator() {
        return userCreator;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tournament that = (Tournament) o;

        if (!name.equals(that.name)) return false;
        if (!userCreator.equals(that.userCreator)) return false;
        if (tournamentComposition != null ? !tournamentComposition.equals(that.tournamentComposition) : that.tournamentComposition != null)
            return false;
        if (game != null ? !game.equals(that.game) : that.game != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + userCreator.hashCode();
        result = 31 * result + (tournamentComposition != null ? tournamentComposition.hashCode() : 0);
        result = 31 * result + (game != null ? game.hashCode() : 0);
        return result;
    }
}
