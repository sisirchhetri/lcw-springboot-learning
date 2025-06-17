package com.elearn.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		
		//routes-urls
		httpSecurity.authorizeHttpRequests(auth->{
			auth.requestMatchers(HttpMethod.GET ,"/api/v1/categories").permitAll()
				.requestMatchers("/client-login","/client-login-process").permitAll()
			//	.requestMatchers(HttpMethod.GET ,"/api/v1/courses").permitAll()
				.anyRequest()
				.authenticated();
		});
		
		httpSecurity.formLogin(
				form ->{
					form.loginPage("/client-login");
					form.usernameParameter("username");
					form.passwordParameter("userpassword");
					form.loginProcessingUrl("/client-login-process");
					form.successForwardUrl("/success"); //- forwards (Post Request)
					//form.defaultSuccessUrl("/success", true); - redirects (Get Request)

				}
			);//Customizer.withDefaults()
		
//		httpSecurity.httpBasic(Customizer.withDefaults());
		
		return httpSecurity.build();
	}
	
}
