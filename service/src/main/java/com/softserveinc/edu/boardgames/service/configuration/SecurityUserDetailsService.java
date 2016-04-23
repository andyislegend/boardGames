//package com.softserveinc.edu.boardgames.service.configuration;
//
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
//import org.springframework.stereotype.Service;
//
//import com.softserveinc.edu.boardgames.persistence.enumeration.UserStatus;
//
//import lombok.Setter;
//
//@Service
//public class SecurityUserDetailsService extends JdbcDaoImpl{
//
//    public static final String DEF_USERS_BY_USERNAME_QUERY = "select password, state from USERS where username = ?";
//    public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = "select u.username as username, ur.value as authority" +
//            " from USERS u" +
//            " join USER_ROLE ur" +
//            " on u.id = ur.username" +
//            " where u.username = ?";
//
//    @Autowired
//    private DataSource dataSource;
//
//    public SecurityUserDetailsService() {
//        super();
//        setUsersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY);
//        setAuthoritiesByUsernameQuery(DEF_AUTHORITIES_BY_USERNAME_QUERY);
//    }
//
//    @PostConstruct
//    private void initialize() {
//        setDataSource(dataSource);
//    }
//
//    @Override
//    protected List<UserDetails> loadUsersByUsername(String username) {
//        return this.getJdbcTemplate().query(getUsersByUsernameQuery(), new String[]{username}, (rs, rowNum) -> {
//            String password = rs.getString(1);
//            boolean enabled = rs.getString(2).equals(UserStatus.ACTIVE.name());
//
//            return new CustomUserDetails(username, password, enabled);
//        });
//    }
//
//    @Override
//    protected UserDetails createUserDetails(String username, UserDetails userDetails, List<GrantedAuthority> authorities) {
//        Class<? extends UserDetails> userDetailsClass = userDetails.getClass();
//
//        if (!userDetailsClass.equals(CustomUserDetails.class)) {
//            throw new InternalAuthenticationServiceException("Provided UserDetails is incorrect: " + userDetailsClass);
//        }
//
//        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
//
//        customUserDetails.setAuthorities(authorities);
//
//        return customUserDetails;
//    }
//
//    public static class CustomUserDetails extends org.springframework.security.core.userdetails.User {
//
//
//        /**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//		
//		@Setter
//        private List<GrantedAuthority> authorities;
//
//
//        public CustomUserDetails(String username, String password, boolean enabled) {
//            super(username, password, enabled, true, true, true, AuthorityUtils.NO_AUTHORITIES);
//        }
//
//
//		@Override
//        public List<GrantedAuthority> getAuthorities() {
//            return authorities;
//        }
//		
//		
//		
//    }
//	
//}
