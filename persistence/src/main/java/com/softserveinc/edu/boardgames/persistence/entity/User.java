package com.softserveinc.edu.boardgames.persistence.entity;

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

/**
 * This class describes users of boardGames website.
 * 
 * 
 * @author Volodymyr Terlyha
 */
@Entity
@Table(name = "users")
public class User {

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
	 * Describes users email.
	 */
	@Column(name = "email")
	private String email;

	/**
	 * Describes users phoneNumber.
	 */
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	/**
	 * Describes users password to website.
	 */
	@Column(name = "password")
	private String password;

	/**
	 * Describes address where user lives. Has a many to one relationship to
	 * address table.
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Address.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = "addressId", referencedColumnName = "id")
	private Address address;
	
//	@OneToOne(fetch = FetchType.LAZY, targetEntity = Rating.class, cascade = { CascadeType.ALL })
//	@JoinColumn(name = "ratingId", referencedColumnName = "id")
//	private Rating rating;
	
//	/**
//	 * Describes address where user lives. Has a many to one relationship to
//	 * address table.
//	 */
//	@OneToMany(fetch = FetchType.LAZY, targetEntity = Friend.class, cascade = { CascadeType.ALL })
//	@JoinColumn(name = "friendId", referencedColumnName = "id")
//	private Set <Friend> friends;
	
//	/**
//	 * Describes users role. Has a one to many relationship to
//	 * roles table.
//	 */
//	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Role.class, cascade = { CascadeType.ALL })
//	@JoinColumn(name = "roleId", referencedColumnName = "id")
//	private Role role;

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
		User other = (User) obj;
		if (id != other.id) {
			return false;
		}
		if (firstName != other.firstName) {
			return false;
		}
		if (lastName != other.lastName) {
			return false;
		}
		if (sex != other.sex) {
			return false;
		}
		if (age != other.age) {
			return false;
		}
		if (email != other.email) {
			return false;
		}
		if (phoneNumber != other.phoneNumber) {
			return false;
		}
		if (password != other.password) {
			return false;
		}
		if (address != other.address) {
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
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + 
				", sex=" + sex + ", age=" + age + ", email="	+ email + ", phoneNumber=" + phoneNumber + 
				", password=" + password + ", adress=" + address + "]";
	}
}

