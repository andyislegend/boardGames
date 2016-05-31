package com.softserveinc.edu.boardgames.persistence.enumeration;

/**
 * provides enum description to User's Status. On success registration is set to
 * {@code UNDER_VERIFICATION}. After User's confirmation via e-mail is re-set to
 * {@code ACTIVE}
 * 
 * @author Andrii Petryk
 *
 */
public enum UserStatus {

	ACTIVE, INACTIVE, DELETED, BANNED, UNDER_VERIFICATION;

}
