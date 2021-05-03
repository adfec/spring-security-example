package com.example.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.security.auth.ApplicationUserService;
import com.example.security.jwt.JwtConfiguration;
import com.example.security.jwt.JwtTokenVerifier;
import com.example.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Allow security per method, so you can avoid the antMatchers
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserService applicationUserService;
	private final JwtConfiguration jwtConfiguration;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService,
			JwtConfiguration jwtConfiguration) {
		this.passwordEncoder = passwordEncoder;
		this.applicationUserService = applicationUserService;
		this.jwtConfiguration = jwtConfiguration;
	}
	// Basic configuration for http security
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		http
			.csrf().disable() // Used only for browser request. Dissabled for API comunication
			// Only for JWT stateless authentication
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No session will be saved
			.and()
			.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfiguration))
			.addFilterAfter(new JwtTokenVerifier(jwtConfiguration), JwtUsernameAndPasswordAuthenticationFilter.class) // To tell srping about the verification filter
			//
			.authorizeRequests()
			.antMatchers("/","index","/css/*","/js/*").permitAll() // Specify the exceptions and allow the exceptions
			.antMatchers("/api/**").hasAuthority("ROL_Administrador") // For the API section, only with that role 
			.anyRequest() // Allow any request
			.authenticated() // Must authenticate
			;
			/* For classic auth
			.and()
			//.httpBasic(); // With basic auth
			.formLogin() // With a default login form
				//.loginPage("/login") // Redirecting to a specific login page
				.permitAll()
				.defaultSuccessUrl("/", true) // In case you need to redirect the user
			.and()
			.rememberMe() // To allow user save login. Works default for two weeks. Checkbox should be named as remember-me
				.tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(20)) // Setting a personalized time
				.key("securityphrase") // Phrase defined to the tokenization
			.and()
			.logout() // Works as default as well by just calling /logout. If using browser, must be using POST
				.logoutUrl("/logut") // Personalized
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID","remember-me")
				.logoutSuccessUrl("/login"); // Where to go after logout
			*/
	}
	
	// Set the provider for users from DAO method
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	// This is how you get users from DAO
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
	}

	/* This is to mock users manually
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		// Building user manually
		UserDetails student = User
								.builder()
								.username("student")
								.password(passwordEncoder.encode("student"))
								.roles(ApplicationUserRoles.STUDENT.name())
								.build();
		
		UserDetails admin = User
								.builder()
								.username("admin")
								.password(passwordEncoder.encode("admin"))
								.roles(ApplicationUserRoles.ADMIN.name())
								.build();
		
		UserDetails adminTrainee = User
									.builder()
									.username("adminT")
									.password(passwordEncoder.encode("adminT"))
									.roles(ApplicationUserRoles.ADMIN_TRAINEE.name())
									.build();
		
		return new InMemoryUserDetailsManager(student, admin, adminTrainee);
	}*/

}
