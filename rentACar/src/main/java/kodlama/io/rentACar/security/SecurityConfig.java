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
		
		return new InMemoryUserDetailsManager(alp);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer ->
				configurer
						.anyRequest().authenticated()
			)
				.formLogin(form ->
						form
								.loginPage("/showMyLoginPage")
								.loginProcessingUrl("/authenticateTheUser")
								.permitAll()
								
				).logout(logout -> logout.permitAll()
								
						
				);
		
		return http.build();
	}
}
