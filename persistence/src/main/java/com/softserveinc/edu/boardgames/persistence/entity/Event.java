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

import com.fasterxml.jackson.annotation.JsonBackReference;



/**
 *  @author Kobevka Anna
 */
@Entity
@Table(name = "events")
public class Event implements Serializable{
	
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
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Game.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = "gameId", referencedColumnName = "id")
	private Game game;

	public Event(){}

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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Event other = (Event) obj;
		if (id != other.id) {
			return false;
		}
		if (game != other.game) {
			return false;
		}
		if (date != other.date) {
			return false;
		}
		
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public String toString() {
	DateFormat datef = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
		return "User [id=" + id + ", name=" + name + ", date=" + datef.format(date) + ", description=" + description + ", author="
				+ user + ", game=" + game + "]";
	}
	
}
