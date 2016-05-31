package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class represents connection between exhcange
 * and gameUsers applier offer to game owner
 * @author Taras Varvariuk
 */
@Entity
@Table(name = "proposition")
public class GameProposition implements Serializable{

	private static final long serialVersionUID = -3974418080870339068L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private GameUser gameUser;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Exchange exchange;

	public GameProposition() {}
	
	public GameProposition(Integer id, GameUser gameUser, Exchange exchange) {
		super();
		this.id = id;
		this.gameUser = gameUser;
		this.exchange = exchange;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GameUser getGameUser() {
		return gameUser;
	}

	public void setGameUser(GameUser gameUser) {
		this.gameUser = gameUser;
	}

	public Exchange getExchange() {
		return exchange;
	}

	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    GameProposition other = (GameProposition) obj;
		return new EqualsBuilder().append(this.getId(), other.getId())
								.append(this.getGameUser(), other.getGameUser())
								.append(this.getExchange(), other.getExchange())
								.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId())
									.append(this.getGameUser())
									.append(this.getExchange())
									.toHashCode();
	}
	

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.getId())
				.append("gameUser", this.getGameUser())
				.append("exchange", this.getExchange())
				.toString();
	}
}
