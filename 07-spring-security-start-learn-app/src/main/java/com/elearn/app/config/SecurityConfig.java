package com.elearn.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
	
/**	For InMemory User Creation and Login
 * @SuppressWarnings("deprecation")
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		
		//Create User
		
		userDetailsManager.createUser(User.withDefaultPasswordEncoder()
										  .username("raj")
										  .password("raj")
										  .roles("ADMIN")
										  .build());
		
		userDetailsManager.createUser(User.withDefaultPasswordEncoder()
				  .username("ram")
				  .password("ram")
				  .roles("ADMIN")
				  .build());
		
		return userDetailsManager;
	}
*/
	
	//For 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		//routes-urls
		httpSecurity.authorizeHttpRequests(auth -> {
			auth.requestMatchers(HttpMethod.GET, "/api/v1/categories").permitAll()
					.requestMatchers("/client-login", "/client-login-process").permitAll()
					//	.requestMatchers(HttpMethod.GET ,"/api/v1/courses").permitAll()
					.anyRequest().authenticated();
		});

		httpSecurity.formLogin(form -> {
			form.loginPage("/client-login");
			form.usernameParameter("username");
			form.passwordParameter("userpassword");
			form.loginProcessingUrl("/client-login-process");
			form.successForwardUrl("/success"); //- forwards (Post Request)
			//form.defaultSuccessUrl("/success", true); - redirects (Get Request)

		});//Customizer.withDefaults()

		//httpSecurity.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/client-login"));

		httpSecurity.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // Allow GET logout
				.logoutSuccessUrl("/client-login?logout"));

		//		httpSecurity.httpBasic(Customizer.withDefaults());

		return httpSecurity.build();
	}

}
