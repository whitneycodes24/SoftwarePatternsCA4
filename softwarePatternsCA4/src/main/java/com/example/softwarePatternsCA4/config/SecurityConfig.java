package com.example.softwarePatternsCA4.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import com.example.softwarePatternsCA4.service.CustomisedUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

	private final CustomisedUserDetailsService userDetailsService;
	
	public SecurityConfig(CustomisedUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    	http
    	  .csrf(csrf -> csrf.disable())
    	  .authorizeHttpRequests(auth -> auth
    	      .requestMatchers("/api/customers/register").permitAll()
    	      .requestMatchers("/api/admin/**").hasRole("ADMIN")
    	      .anyRequest().authenticated()
    	  )
    	  .httpBasic();


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setUserDetailsService(userDetailsService);
    	provider.setPasswordEncoder(passwordEncoder());
    	return provider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    
}
