package com.blog.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class securityConfig {
	
	//1.getting user details service as a bean.
		@Bean
		public UserDetailsService getUserDetailsService() {
			return new UserDetailsServiceImpl();

		}
		//2.beaning the BCryptepassword encoder.
		@Bean
		public BCryptPasswordEncoder passwordEncoder()
		{
			return new BCryptPasswordEncoder();
		}
		
		//3. beaning the DAO authenticator to config the user details.
		@Bean
		public DaoAuthenticationProvider daoAuthenticationProvider()
		{
			DaoAuthenticationProvider  daoAuthenticationProvider= new DaoAuthenticationProvider();
			daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
			daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
			return daoAuthenticationProvider;
		}

		@Bean
		protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
			http
			.authorizeHttpRequests()
			.requestMatchers("/post/**").hasRole("USER")
			.requestMatchers("/**").permitAll()
			.and().formLogin().loginPage("/signin").loginProcessingUrl("/signin").defaultSuccessUrl("/post/posthome").and().csrf().disable();
			
			
			http.authenticationProvider(daoAuthenticationProvider());
			return http.build();
		}
}
