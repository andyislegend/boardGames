package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;





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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Category other = (Category) obj;
        if (id != other.id) {
            return false;
        }       
        if (name != other.name) {
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
        return "Category [id=" + id + ", name=" + name + "]";
    }
}
