package kodlama.io.rentACar.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails alp = User.builder()
				.username("alp")
				.password("{noop}5561")
				.roles("ADMIN")
				.build();
		
		UserDetails batu = User.builder()
				.username("batu")
				.password("{noop}5561")
				.roles("CUSTOMER")
				.build();
		return new InMemoryUserDetailsManager(alp, batu);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer ->
				configurer
				.requestMatchers("/").permitAll()
				.requestMatchers("/models/list").permitAll()
				.requestMatchers("/brands/list").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
				.formLogin(form ->
						form
								.loginPage("/showMyLoginPage")
								.loginProcessingUrl("/authenticateTheUser")
								.permitAll()
								
				).logout(logout -> logout.permitAll()
												
						
				)
				.exceptionHandling(configurer ->
							configurer.accessDeniedPage("/access-denied"));
		
		return http.build();
	}
}
