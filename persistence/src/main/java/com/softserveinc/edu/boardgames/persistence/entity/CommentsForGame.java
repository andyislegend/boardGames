package com.softserveinc.edu.boardgames.persistence.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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
public class CommentsForGame implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = GameUser.class, cascade = CascadeType.MERGE)
    private GameUser gameUser;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, cascade = CascadeType.MERGE)
    private User user;

    @Column(name = "text")
    private String text;
    
    @Column(name = "dateOfComment")
    private Date date;

    public CommentsForGame(){
    	this.date = Calendar.getInstance().getTime();
    }

    public CommentsForGame(GameUser gameUser, User user, String text, Date date){
        this.gameUser = gameUser;
        this.user = user;
        this.text = text;
        this.date = Calendar.getInstance().getTime();
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
    
    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
