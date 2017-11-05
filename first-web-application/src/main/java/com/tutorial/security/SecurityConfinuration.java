package com.tutorial.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfinuration extends WebSecurityConfigurerAdapter {
	
	@Autowired //define valid users
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("Alisa").password("mypwd")
				.roles("USER", "ADMIN");
	}

	@Override //configure which urls are secure
	protected void configure(HttpSecurity http) throws Exception {
		// /login is exposed by Spring Security
		http.authorizeRequests().antMatchers("/login").permitAll() 
				//anything with *todo* has to be secure. USER is allowed to view *todo* urls 
				.antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
				//otherwise the login page will be shown.
				.formLogin(); 
	}
}
