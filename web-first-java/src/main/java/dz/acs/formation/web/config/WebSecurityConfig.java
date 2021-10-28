package dz.acs.formation.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
		
	@Autowired
	private UserDetailsService userDetailsService; 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/logout").permitAll()
				.antMatchers("/formation/projets/**").hasRole("ADMIN")
				.antMatchers("/formation/session/**").hasRole("USER")
				.antMatchers("/formation/**").authenticated()
				.antMatchers("/**").permitAll()
			.and()
				.csrf()
			.and()
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login?authentication=failure")
				.defaultSuccessUrl("/index")
				.passwordParameter("password")
				.usernameParameter("username")
			.and()
				.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login?logout")
			.and()
				.exceptionHandling()
				.accessDeniedPage("/denied");
			
	}
	
	//@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder enc=new BCryptPasswordEncoder();
		//System.out.println( enc.encode("password"));
		return enc;
		//return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);		
	}
}
