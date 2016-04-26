package com.softserveinc.edu.boardgames.persistence.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Volodymyr Krokhmaliuk
 *
 */
@Entity
@Table(name = "comments")
public class CommentsForGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY,targetEntity = GameUser.class, cascade = CascadeType.MERGE)
    private GameUser gameUser;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, cascade = CascadeType.MERGE)
    private User user;

    @Column(name = "text")
    private String text;

    public CommentsForGame(){

    }

    public CommentsForGame(GameUser gameUser, User user, String text){
        this.gameUser = gameUser;
        this.user = user;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public GameUser getGameUser() {
        return gameUser;
    }

    public void setGameUser(final GameUser gameUser) {
        this.gameUser = gameUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
