package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * This class describes addresses where users of boardGames website live.  
 * 
 * This Address entity has a one to many relationship with the User
 * entity.
 * 
 * @author Volodymyr Terlyha
 */
@Entity
@Table (name = "address")
public class Address implements Serializable {

	/**
     * Describes the address id. Unique value.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)        
    private Integer id;
    
    /**
     * Describes the country where user lives.
     */
    @Column(name = "country")
    private String country;
    
    /**
     * Describes the city where user lives.
     */
    @Column(name = "city")
    private String city;
    
    /**
     * Describes the post code where user lives.
     */
    @Column(name = "postCode")
    private Integer postCode;
    
    /**
     * Describes the street where user lives.
     */
    @Column(name = "street")
    private String street;
    
    /**
     * Describes the houseNumber where user lives.
     */
    @Column(name = "houseNumber")
    private Integer houseNumber;
    
    /**
     * Describes the roomNumberNumber where user lives.
     */
    @Column(name = "roomNumber")
    private Integer roomNumber;
    
    /**
     * Describes all the users that are associated with this address.
     * Has a one to many relationship to users table.
     */    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="address")
    private Set<User> users;
    
    /**
     * Constructor without parameters.
     */ 
    public Address() {
    }
    
    /**
     * Get value of column id.
     * 
     * @return value of column id.
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Set value of column id.
     * 
     * @param id
     *            value of column id.
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * Get value of column country.
     * 
     * @return value of column country.
     */
    public String getCountry() {
		return country;
	}
    
    /**
     * Set value of column country.
     * 
     * @param country
     *            value of column country.
     */
    public void setCountry(String country) {
		this.country = country;
	}
    
    /**
     * Get value of column city.
     * 
     * @return value of column city.
     */
    public String getCity() {
        return city;
    }
    
    /**
     * Set value of column city.
     * 
     * @param city
     *            value of column city.
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * Get value of column postCode.
     * 
     * @return value of column postCode.
     */
    public Integer getPostCode() {
		return postCode;
	}
    
    /**
     * Set value of column postCode.
     * 
     * @param postCode
     *            value of column postCode.
     */
    public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}
    
    /**
     * Get value of column street.
     * 
     * @return value of column street.
     */
    public String getStreet() {
        return street;
    }
    
    /**
     * Set value of column street.
     * 
     * @param street
     *            value of column street.
     */
    public void setStreet(String street) {
        this.street = street;
    }
    
    /**
     * Get value of column houseNumber.
     * 
     * @return value of column houseNumber.
     */
    public Integer getHouseNumber() {
        return houseNumber;
    }
    
    /**
     * Set value of column houseNumber.
     * 
     * @param houseNumber
     *            value of column houseNumber.
     */
    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }
    
    /**
     * Get value of column roomNumber.
     * 
     * @return value of column roomNumber.
     */
    public Integer getRoomNumber() {
        return roomNumber;
    }
    
    /**
     * Set value of column roomNumber.
     * 
     * @param roomNumber
     *            value of column roomNumber.
     */
    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }    
    
    /**
     * Get all users associated with this address.
     * 
     * @return all users associated with this address.
     */
    public Set<User> getUsers() {
		return users;
	}
    
    /**
     * Changes list of users for this address.
     * 
     * @param users
     *            value of all users for this address.
     */
	public void setUsers(Set<User> users) {
		this.users = users;
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
        Address other = (Address) obj;
        if (id != other.id) {
            return false;
        }       
        if (country != other.country) {
            return false;
        }
        if (city != other.city) {
            return false;
        }
        if (postCode != other.postCode) {
            return false;
        }
        if (street != other.street) {
            return false;
        }
        if (houseNumber != other.houseNumber) {
            return false;
        }
        if (roomNumber != other.roomNumber) {
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
        return "Adress [id=" + id + ", country=" + country + ", city=" + city + ", postCode=" + postCode +
        		", street=" + street + ", houseNumber=" + houseNumber + ", rooomNumber=" + roomNumber + "]";
    }
}

