package dz.acs.formation.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService; 

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/api/v1/**");
	}
	
	
	
	@Configuration
    @Order(1)                                                        
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
        	 // @formatter:off
    		http
    			.antMatcher("/api/v1/**")
    			.sessionManagement()
            			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            		
            	.and()
            		.csrf().disable()
            	.authorizeRequests()
    				.antMatchers(HttpMethod.GET,"/api/v1/**").hasAnyRole("REST","ADMIN","USER")
    				.antMatchers(HttpMethod.POST,"/api/v1/**").hasRole("ADMIN")
    				.antMatchers(HttpMethod.DELETE,"/api/v1/**").hasRole("ADMIN")
    				.antMatchers(HttpMethod.PUT,"/api/v1/**").hasRole("ADMIN")
    				.antMatchers(HttpMethod.OPTIONS,"/api/v1/**").permitAll()
    			.and()
    				.httpBasic();
    		// @formatter:on
        }
    }
	
	@Configuration
	@Order(10)  
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        AuthenticationSuccessWithSessionHandler successHandler = new AuthenticationSuccessWithSessionHandler();

        @Override
        protected void configure(HttpSecurity http) throws Exception {
        	// @formatter:off
    		http.authorizeRequests()
    			.antMatchers("/login").permitAll()
    			.antMatchers("/logout").permitAll()
    			.antMatchers("/formation/projets/**").hasRole("ADMIN")
    			.antMatchers("/formation/session/**").hasRole("USER")
    			.antMatchers("/formation/**").authenticated()
    			.antMatchers("/api/wss/**").anonymous()//for websockets demo
    			.antMatchers("/**").permitAll()
    		.and()
    			.csrf()
    		.and()
    			.formLogin()
    				.loginPage("/login")
    				.failureUrl("/login?authentication=failure")
    				.defaultSuccessUrl("/index")
    				.successHandler(successHandler)
    				.passwordParameter("password")
    				.usernameParameter("username")
    		.and()
    			.logout()
    				.logoutUrl("/logout")
    				.logoutSuccessUrl("/login?logout")
    				.logoutSuccessHandler(successHandler)
    		.and()
    			.exceptionHandling()
    				.accessDeniedPage("/denied");
    		
    		// @formatter:on
        }
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
