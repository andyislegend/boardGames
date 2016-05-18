package com.softserveinc.edu.boardgames.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class VerificationToken implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4058412545610441245L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public VerificationToken() {
        super();
    }

    public VerificationToken(final String token) {
        super();

        this.token = token;
    }

    public VerificationToken(final String token, final User user) {
        super();

        this.token = token;
        this.user = user;
    }
 
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    @Override
	public int hashCode() {
		 return new HashCodeBuilder().append(getId())
				 .append(getToken())
				 .append(getUser())				 
                 .toHashCode();
	}

    @Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    VerificationToken other = (VerificationToken) obj;
	    return new EqualsBuilder().append(getId(), other.getId())
	                              .append(getToken(), other.getToken())
	                              .append(getUser(), other.getUser())
	                              .isEquals();
	}
    

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("token", getToken())
                .toString();
    }

}
	
