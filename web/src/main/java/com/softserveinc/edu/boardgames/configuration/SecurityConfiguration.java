package com.softserveinc.edu.boardgames.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;

import com.allanditzel.springframework.security.web.csrf.CsrfTokenResponseHeaderBindingFilter;

/**
 * 
 * @author Andrii Petryk
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		CsrfTokenResponseHeaderBindingFilter csrfTokenFilter = new CsrfTokenResponseHeaderBindingFilter();
        http.addFilterAfter(csrfTokenFilter, CsrfFilter.class);
        
        
		http
			
			
			.authorizeRequests()
			.antMatchers("/", "/index").permitAll()
			.antMatchers("/home/**").fullyAuthenticated()
			.and()
			.formLogin()
			.loginProcessingUrl("/authenticate")
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(new AjaxAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler()))
			.loginPage("/index")
			.and()
            .csrf().disable()
            .httpBasic()
            .and()
            .logout()
    		.invalidateHttpSession(true)
    		.logoutSuccessUrl("/")
    		.logoutUrl("/logout")
    		.permitAll();

	}
}