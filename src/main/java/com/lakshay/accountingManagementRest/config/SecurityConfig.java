package com.lakshay.accountingManagementRest.config;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final UserDetailsService userDetailsService;

	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
							.csrf().disable() //occur when session and cookies used to authenticate,to secure our app from cross site scripting
							.authorizeRequests() // allow all authrization requests that starts with /api/auth/
							.antMatchers("/api/auth/**")  //any other request that doest match must be authenticated or checked  
							.permitAll()
							.antMatchers(HttpMethod.GET,"/api/subreddit")//allow all requests on /api/subreddits
							.permitAll()
							.antMatchers(HttpMethod.GET, "/api/posts/")
							.permitAll()
							.antMatchers(HttpMethod.GET, "/api/posts/**")
							.permitAll()
							.anyRequest()
							.authenticated();

	}
	

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService)
									.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
 
}
