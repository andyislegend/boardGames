package com.softserveinc.edu.boardgames.service.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.enumeration.UserRoles;
import com.softserveinc.edu.boardgames.service.UserService;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
    private UserService userService;
	
	@Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userService.getUser(username);
        if(user==null){
            throw new UsernameNotFoundException("Username not found"); 
        }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
                 user.getState().equals("ACTIVE"), true, true, true, getGrantedAuthorities(user));
    }
	
	private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for(UserRoles userRoles : user.getUserRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userRoles.toString()));
        }
        System.out.print("authorities :"+authorities);
        return authorities;
    }
}
