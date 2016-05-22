package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *  @author Kobevka Anna
 */
@Entity
@Table(name = "events")
public class Event implements Serializable{
	
	private static final long serialVersionUID = -8068483407568107770L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;

	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "place")
	private String place;
	
	@Column(name = "imgsrc")
	private String imgsrc;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class, cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Game.class, cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "gameId", referencedColumnName = "id")
	private Game game;

	public Event(){}
	
	
	public String getImgsrc() {
		return imgsrc;
	}



	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
    public boolean equals(Object obj) {
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
