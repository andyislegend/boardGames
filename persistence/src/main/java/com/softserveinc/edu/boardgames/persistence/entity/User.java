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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.softserveinc.edu.boardgames.persistence.enumeration.UserRating;
import com.softserveinc.edu.boardgames.persistence.enumeration.UserRoles;
import com.softserveinc.edu.boardgames.persistence.enumeration.UserStatus;

/**
 * This class describes users of boardGames website.
 * 
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
	 * Describes users first name.
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
	@Column(name = "sex")
	private String sex;

	/**
	 * Describes users sex.
	 */
	@Column(name = "age")
	private Integer age;

	/**
	 * Describes users email. Also is used as a login.
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
	 * Provides a description of User's current rating. By default, after
	 * registration User obtain a 'NOOB' rating
	 */
	@NotEmpty
	@Column(name = "rating", nullable = false)
	private String rating = UserRating.NOOB.name();

	/**
	 * Provides a description of User's current status. By default, after
	 * registration User obtain 'ACTIVE' state
	 */
	@NotEmpty
	@Column(name = "state", nullable = false)
	private String state = UserStatus.ACTIVE.name();

	/**
	 * Describes address where user lives. Has a many to one relationship to
	 * address table.
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Address.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = "addressId", referencedColumnName = "id")
	private Address address;

	/*
	 * by Anna for Events in case you need to change smth, please let me know
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Event> events;

	// /**
	// * Describes address where user lives. Has a many to one relationship to
	// * address table.
	// */
	// @OneToMany(fetch = FetchType.LAZY, targetEntity = Friend.class, cascade =
	// { CascadeType.ALL })
	// @JoinColumn(name = "friendId", referencedColumnName = "id")
	// private Set <Friend> friends;

	/**
	 * Describes users role. Has a one to many relationship to roles table.
	 */
	@ElementCollection
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "username"))
    @Enumerated(EnumType.STRING)
    @Column(name = "value", length = 30)
	private Set<UserRoles> userRoles = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
	private Set<GameUser> gameUsers;

	/**
	 * List of tounaments that were organized by user
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.REFRESH }, mappedBy = "userCreator")
	private List<Tournament> createdTounaments;

	/**
	 * List if tounaments which user take part in
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.REFRESH }, mappedBy = "userGuest")
	private List<TournamentComposition> takenpartTounaments;

	/**
	 * Constructor without parameters.
	 */
	public User() {
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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Get value of column firstName.
	 * 
	 * @return value of column firstName.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set value of column firstName.
	 * 
	 * @param firstName
	 *            value of column firstName.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get value of column lastName.
	 * 
	 * @return value of column lastName.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set value of column lastName.
	 * 
	 * @param lastName
	 *            value of column lastName.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get value of column sex.
	 * 
	 * @return value of column sex.
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Set value of column sex.
	 * 
	 * @param sex
	 *            value of column sex.
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Get value of column age.
	 * 
	 * @return value of column age.
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Set value of column age.
	 * 
	 * @param age
	 *            value of column age.
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * Get value of column email.
	 * 
	 * @return value of column email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set value of column email.
	 * 
	 * @param email
	 *            value of column email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get value of column phoneNumber.
	 * 
	 * @return value of column phoneNumber.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set value of column phoneNumber.
	 * 
	 * @param phoneNumber
	 *            value of column phoneNumber.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Get value of column password.
	 * 
	 * @return value of column password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set value of column password.
	 * 
	 * @param password
	 *            value of column password.
	 */
	public void setPassword(String password) {
		this.password = password;
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
	
	/**
	 * Get value of column address.
	 * 
	 * @return value of column address.
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Set value of column address.
	 * 
	 * @param address
	 *            value of column address.
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<UserRoles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((createdTounaments == null) ? 0 : createdTounaments.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((events == null) ? 0 : events.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gameUsers == null) ? 0 : gameUsers.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((takenpartTounaments == null) ? 0 : takenpartTounaments.hashCode());
		result = prime * result + ((userRoles == null) ? 0 : userRoles.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (createdTounaments == null) {
			if (other.createdTounaments != null)
				return false;
		} else if (!createdTounaments.equals(other.createdTounaments))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (events == null) {
			if (other.events != null)
				return false;
		} else if (!events.equals(other.events))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gameUsers == null) {
			if (other.gameUsers != null)
				return false;
		} else if (!gameUsers.equals(other.gameUsers))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (rating != other.rating)
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (state != other.state)
			return false;
		if (takenpartTounaments == null) {
			if (other.takenpartTounaments != null)
				return false;
		} else if (!takenpartTounaments.equals(other.takenpartTounaments))
			return false;
		if (userRoles == null) {
			if (other.userRoles != null)
				return false;
		} else if (!userRoles.equals(other.userRoles))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", sex=" + sex
				+ ", age=" + age + ", email=" + email + ", phoneNumber=" + phoneNumber + ", rating=" + rating
				+ ", state=" + state + ", address=" + address + "]";
	}

	
}
