package com.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		//		
//		http.authorizeRequests().antMatchers("/home","/login","/register").permitAll()
//		.anyRequest().authenticated().and().httpBasic();
//		http.authorizeRequests().antMatchers("/public/**").permitAll()
//		.anyRequest().authenticated().and().httpBasic();
		
		http.csrf().disable().
		authorizeRequests()
		.antMatchers("/signin").permitAll()
		.antMatchers("/public/**").hasRole("NORMAL")
		.antMatchers("/users/**").hasRole("ADMIN")
		.anyRequest().authenticated().and().formLogin()
		.loginPage("/signin")
		.loginProcessingUrl("/dologin")
		.defaultSuccessUrl("/users/");
		
	}
	
	//ROLE - High level overview ->Normal :read
	//authority - permission-->READ
	//ADMIN -- READ,WRITE,UPDATE

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("john").password("abc").roles("NORMAL");ADMIN
		auth.inMemoryAuthentication().withUser("john").password(passwordEncoder().encode("abcd")).roles("NORMAL");
		auth.inMemoryAuthentication().withUser("kapil").password(passwordEncoder().encode("abcde")).roles("ADMIN");
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder(10);
	}
	
	

}
