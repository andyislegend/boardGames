package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.softserveinc.edu.boardgames.persistence.enumeration.UserLevel;
import com.softserveinc.edu.boardgames.persistence.enumeration.UserRoles;
import com.softserveinc.edu.boardgames.persistence.enumeration.UserStatus;

/**
 * This class describes users of boardGames website.
 * 
 * @author Volodymyr Terlyha
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2966070992511907457L;

	/**
	 * Describes the user id. Unique value.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * Describes users username(login).
	 */
	@NotEmpty
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	/**
	 * Describes users first name.
	 */
	@Column(name = "firstName")
	private String firstName;

	/**
	 * Describes users last name.
	 */
	@Column(name = "lastName")
	private String lastName;

	/**
	 * Describes users sex.
	 */
	@Column(name = "gender")
	private String gender;

	/**
	 * Describes users age.
	 */
	@Column(name = "age")
	private Integer age;

	/**
	 * Describes users email.
	 * 
	 */
	@NotEmpty
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	/**
	 * Describes users phoneNumber.
	 */
	@Column(name = "phoneNumber")
	private String phoneNumber;

	/**
	 * Describes users password to website.
	 */
	@NotEmpty
	@Column(name = "password", nullable = false)
	private String password;

	/**
	 * Provides a description of User's current rating as a number. By default,
	 * after registration User obtain 0 rating
	 */
	@NotNull
	@Column(name = "userRating", nullable = false)
	private Integer userRating = 0;

	/**
	 * Provides a description of User's current rating. By default, after
	 * registration User obtain a 'NOOB' rating
	 */
	@NotEmpty
	@Column(name = "rating", nullable = false)
	private String rating = UserLevel.NOOB.name();

	/**
	 * Provides a description of User's current status. By default, after
	 * registration User obtain 'ACTIVE' state
	 */
	@NotEmpty
	@Column(name = "state", nullable = false)
	private String state = UserStatus.ACTIVE.name();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private Set<GameUser> userGames;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private Set<Exchange> exchanges;
	
	/**
	 * Describes address where user lives. Has a many to one relationship to
	 * address table.
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL})
	@JoinColumn(name = "addressId", referencedColumnName = "id")
	private Address address;

	/**
	 * Describes users role. Has a one to many relationship to roles table.
	 */
	@ElementCollection
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "username"))
	@Enumerated(EnumType.STRING)
	@Column(name = "value", length = 30)
	private Set<UserRoles> userRoles = new HashSet<>();

	/**
	 * List of tounaments that were organized by user
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "user")
	@JsonBackReference
	private Set<GameRatingNumeric> gameRatingNumeric;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.REFRESH }, mappedBy = "userCreator")
	@JsonBackReference
	private List<Tournament> createdTounaments;

	public User() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserRating() {
		return userRating;
	}

	public void setUserRating(Integer userRating) {
		this.userRating = userRating;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<UserRoles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}

	public Set<GameRatingNumeric> getGameRatingNumeric() {
		return gameRatingNumeric;
	}

	public void setGameRatingNumeric(Set<GameRatingNumeric> gameRatingNumeric) {
		this.gameRatingNumeric = gameRatingNumeric;
	}
	
	@PostUpdate
	@PreUpdate
	public void changeUser() {
		if(this.getUserRating() < -5) {
			this.setState(UserStatus.BANNED.name());
		}	
		
		if (isBetween(this.getUserRating(), 1, 10)) {
			this.setRating(UserLevel.SKILLED.name());
			return;
		}
		
		if (isBetween(this.getUserRating(), 11, 20)) {
			this.setRating(UserLevel.PRO.name());
			return;
		}
		
		if (isBetween(this.getUserRating(), 21, 30)) {
			this.setRating(UserLevel.VETERAN.name());
			return;
		}
		
		if (isBetween(this.getUserRating(), 31, 50)) {
			this.setRating(UserLevel.MASTER.name());
			return;
		}
		
		if (isBetween(this.getUserRating(), 51, 70)) {
			this.setRating(UserLevel.WICKED_SICK.name());
			return;
		}
		
		if (isBetween(this.getUserRating(), 71, 90)) {
			this.setRating(UserLevel.EXTRATERESTRIAL.name());
			return;
		}
		
		if (isBetween(this.getUserRating(), 91, 100)) {
			this.setRating(UserLevel.GODLIKE.name());
			return;
		}
	}
	
	private boolean isBetween(int usersRating, int lower, int upper) {
		  return lower <= usersRating && usersRating <= upper;
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