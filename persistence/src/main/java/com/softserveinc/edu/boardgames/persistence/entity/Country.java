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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class describes country where user of boardGames website live and where
 * tournaments and events are held.
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
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "country", fetch = FetchType.LAZY)
	private Set<User> usersCountry;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "country", fetch = FetchType.LAZY)
	private Set<City> citiesCountry;
	
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
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null) {
	        return false;
	    }
	    if (getClass() != obj.getClass()) {
	        return false;
	    }
	    Country other = (Country) obj;
	    return new EqualsBuilder().append(getId(), other.getId())
	                              .append(getName(), other.getName())
	                              .isEquals();
	}
	
	@Override
	public int hashCode() {
		 return new HashCodeBuilder().append(getId())
				 .append(getName())
                 .toHashCode();
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this)
        		.append("id", getId())
                .append("name", getName())
                .toString();
    }
}