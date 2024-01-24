package com.ss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ss.security.JwtAthenticationFilter;
import com.ss.security.JwtAuthenticationEntryPoint;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAthenticationFilter filter;

	
//	@Autowired
//	private JwtAuthenticationEntryPoint entryPoint;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		// Hardcoding a single user for simplicity
		UserDetails user = User.builder().username("rt").password(passwordEncoder().encode("rt")).roles("USER").build();

		return new InMemoryUserDetailsManager(user);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
//		http
//				.csrf().disable()
//				.cors().disable().authorizeRequests()
//				.antMatchers("/token", "/swagger-ui/**", "/swagger-ui", "/v2/api-docs/**", "/swagger-resources/**",
//						"/swagger-ui.html/**", "/swagger-ui.html")
//				.permitAll()
//				// .antMatchers("/**").permitAll()
//				.anyRequest().authenticated().and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.csrf().disable()
			.cors().disable()
			.authorizeHttpRequests()
			.antMatchers("/home/hello").permitAll()
			.anyRequest().authenticated()
			.and().formLogin()
//			.and().
//			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			;
		
//		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
