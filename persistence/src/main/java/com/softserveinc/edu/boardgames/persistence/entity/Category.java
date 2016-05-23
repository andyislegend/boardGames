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
 * Every game belongs to particular category
 * This entity represents game category data
 * Contains one-to-many relationship with entity Game
 * @author Varvariuk Taras
 *
 */
@Entity
@Table(name = "category")
public class Category implements Serializable{
	
	private static final long serialVersionUID = 1362760535235604358L;

	/**
	 * Unique value, primary key to categories
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	/**
	 * Game category name
	 */
	@Column(name = "name" )
	private String name;
	
	/**
	 * A set of games that belongs to current category
	 * Mapped from Game entity - OneToMany relationship
	 */

	/**
	 * Default constructor
	 */
	public Category() {}
	
	/**
	 * Aditional constructor
	 * @param id - id number
	 */
	public Category(Integer id) {
		this.id = id;
	}
	
	/**
	 * Aditional constructor
	 * @param name - category name
	 */
	public Category(String name) {
		
		this.name = name;
	}
	
	/**
	 * @return id - primary key
	 */
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return name - category name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name - setter value for category name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return games return the list of games
	 */
	
	
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
