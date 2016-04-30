package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class describes country where user of boardGames website live and where
 * tournaments and events are held.
 * 
 * This Country entity has a one to many relationship with the Address entity
 * and one to many relationship with City entity.
 * 
 * @author Volodymyr Terlyha
 */
@Entity
@Table(name = "country")
public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2966070992511907461L;

	/**
	 * Describes country's id. Unique value.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * Describes country's name where user lives.
	 */
	@Column(name = "name")
	private String name;
	
	public Country() {
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