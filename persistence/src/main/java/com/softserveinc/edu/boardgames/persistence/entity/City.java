package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class describes city where users of boardGames website live and where
 * tournaments and events are held.
 * 
 * This City entity has a one to many relationship with the Address entity and
 * many to one relationship with Country entity.
 * 
 * @author Volodymyr Terlyha
 */
@Entity
@Table(name = "city")
public class City implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2966070992511907465L;

	/**
	 * Describes city's id. Unique value.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * Describes city's name where user lives.
	 */
	@Column(name = "name")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "countryId", referencedColumnName = "id")
	private Country country;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "city", fetch = FetchType.LAZY)
	private Set<User> usersCity;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "city", fetch = FetchType.LAZY)
	private List<Event> events;
	
	public City() {
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
	
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Event> getEvent() {
		return events;
	}

	public void setEvent(List<Event> event) {
		this.events = event;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    City other = (City) obj;
	    return new EqualsBuilder().append(getId(), other.getId())
	                              .append(getName(), other.getName())
	                              .append(getCountry(), other.getCountry())
	                              .isEquals();
	}
	
	@Override
	public int hashCode() {
		 return new HashCodeBuilder().append(getId())
				 .append(getName())
				 .append(getCountry())
                 .toHashCode();
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this)
        		.append("id", getId())
                .append("name", getName())
                .append("country", getCountry())
                .toString();
    }
}