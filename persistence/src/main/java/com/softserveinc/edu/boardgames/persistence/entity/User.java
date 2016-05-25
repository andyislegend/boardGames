package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;
import java.util.HashSet;
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
import javax.persistence.OneToOne;
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
	private String level = UserLevel.NOOB.name();

	/**
	 * Provides a description of User's current status. By default, after
	 * registration User obtain 'ACTIVE' state
	 */
	@NotEmpty
	@Column(name = "state", nullable = false)
	private String state = UserStatus.UNDER_VERIFICATION.name();

/*	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<GameUser> userGames;*/
	
	@Column(name = "tournamentRatingStatus")
	private boolean tournamentRatingStatus;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private Set<Exchange> exchanges;
	
	/**
	 * Describes the country where user lives.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "countryId", referencedColumnName = "id")
	private Country country;

	/**
	 * Describes the city where user lives.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cityId", referencedColumnName = "id")
	private City city;

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
	private Set<GameRating> gameRatingNumeric;

	@ElementCollection
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "users")
	private Set<Tournament> tournaments;
	
//	/**
//	 * Describes connection to events table.
//	 */
//	@ElementCollection
//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = "subscribed_events",joinColumns = {@JoinColumn(name = "user_id")},
//	inverseJoinColumns = {@JoinColumn(name = "event_id")})
//	private Set<Event> events;

	/**
	 * Describes a connection to verification token entity
	 */
	@OneToOne (mappedBy="user", cascade = CascadeType.ALL)
	private VerificationToken verificationToken;

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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	public boolean isTournamentRatingStatus() {
		return tournamentRatingStatus;
	}

	public void setTournamentRatingStatus(boolean tournamentRatingStatus) {
		this.tournamentRatingStatus = tournamentRatingStatus;
	}

	public Set<UserRoles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}

	public Set<GameRating> getGameRatingNumeric() {
		return gameRatingNumeric;
	}

	public void setGameRatingNumeric(Set<GameRating> gameRatingNumeric) {
		this.gameRatingNumeric = gameRatingNumeric;
	}
	
	public Set<Tournament> getTournaments() {
		return tournaments;
	}

	public void setTournaments(Set<Tournament> tournaments) {
		this.tournaments = tournaments;
	}

	public VerificationToken getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(VerificationToken verificationToken) {
		this.verificationToken = verificationToken;
	}

/*	public Set<GameUser> getUserGames() {
		return userGames;
	}

	public void setUserGames(Set<GameUser> userGames) {
		this.userGames = userGames;
	}*/

	@PreUpdate
	public void changeUserLevelOrStatus() {
		if(getUserRating() < -5) {
			setState(UserStatus.BANNED.name());
		} else if (isBetween(this.getUserRating(), 0, 10)) {
			setLevel(UserLevel.NOOB.name());
		} else if (isBetween(this.getUserRating(), 11, 20)) {
			setLevel(UserLevel.SKILLED.name());
		} else if (isBetween(this.getUserRating(), 21, 30)) {
			this.setLevel(UserLevel.PRO.name());
		} else if (isBetween(this.getUserRating(), 31, 40)) {
			this.setLevel(UserLevel.VETERAN.name());
		} else if (isBetween(this.getUserRating(), 41, 50)) {
			this.setLevel(UserLevel.MASTER.name());
		} else if (isBetween(this.getUserRating(), 51, 60)) {
			this.setLevel(UserLevel.WICKED_SICK.name());
		} else if (isBetween(this.getUserRating(), 61, 70)) {
			this.setLevel(UserLevel.EXTRATERESTRIAL.name());
		} else if (isBetween(this.getUserRating(), 71, 100)) {
			this.setLevel(UserLevel.GODLIKE.name());
		}
	}
	
	private boolean isBetween(int usersRating, int lower, int upper) {
		  return lower <= usersRating && usersRating <= upper;
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
	    return new EqualsBuilder().append(getId(), other.getId())
	                              .append(getUsername(), other.getUsername())
	                              .append(getFirstName(), other.getFirstName())
	                              .append(getLastName(), other.getLastName())
	                              .append(getGender(), other.getGender())
	                              .append(getAge(), other.getAge())
	                              .append(getEmail(), other.getEmail())
	                              .append(getPhoneNumber(), other.getPhoneNumber())
	                              .append(getPassword(), other.getPassword())
	                              .append(getUserRating(), other.getUserRating())
	                              .append(getLevel(), other.getLevel())
	                              .append(getState(), other.getState())
	                              .append(getCountry(), other.getCountry())
	                              .append(getCity(), other.getCity())
	                              .isEquals();
	}
	
	@Override
	public int hashCode() {
		 return new HashCodeBuilder().append(getId())
				 .append(getUsername())
				 .append(getFirstName())
				 .append(getLastName())
				 .append(getGender())
				 .append(getAge())
				 .append(getEmail())
				 .append(getPhoneNumber())
				 .append(getPassword())
				 .append(getUserRating())
				 .append(getLevel())
				 .append(getState())
				 .append(getCountry())
				 .append(getCity())
                 .toHashCode();
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("username", getUsername())
                .append("firstName", getFirstName())
                .append("lastName", getLastName())
                .append("gender", getGender())
                .append("age", getAge())
                .append("email", getEmail())
                .append("phoneNumber", getPhoneNumber())
                .append("password", getPassword())
                .append("userRating", getUserRating())
                .append("rating", getLevel())
                .append("state", getState())
                .append("country", getCountry())
                .append("city", getCity())
                .toString();
    }
}