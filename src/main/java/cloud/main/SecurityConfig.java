/**
 * SecurityConfig.java
 * 
 * Cody Crosby
 * GCU, 2025
 * 
 * This class configures the security settings for the application.
 * It defines the security filter chain, authentication manager, and password encoder.
 * The security filter chain specifies which URLs are public and which require authentication.
 * The authentication manager is responsible for authenticating users.
 * The password encoder is used to encode passwords for secure storage.
 */
package cloud.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import cloud.main.business.LoginService;
import cloud.main.utilities.ApiTokenFilter;
import cloud.main.utilities.LoginSuccessHandler;

/**
 * This class configures the security settings for the application.
 * It defines the security filter chain, authentication manager, and password encoder.
 * The security filter chain specifies which URLs are public and which require authentication.
 * The authentication manager is responsible for authenticating users.
 * The password encoder is used to encode passwords for secure storage.
 */
@Configuration
public class SecurityConfig {

    private final LoginService loginService;
    @Autowired
    private LoginSuccessHandler successHandler;

    public SecurityConfig(LoginService loginService) {							// Constructor for SecurityConfig
        this.loginService = loginService;										// LoginService object
    }
    /**
	 * This method configures the security filter chain for the application.
	 * It specifies which URLs are public and which require authentication.
	 * It also configures form login and logout behavior.
	 *
	 * @param http the HttpSecurity object to configure
	 * @return the configured SecurityFilterChain
	 * @throws Exception if an error occurs during configuration
	 */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())										// disable CSRF for testing purposes only	

            // public paths
            .authorizeHttpRequests(auth -> auth									// authorizeHttpRequests() method
                .requestMatchers("/", 
                				 "/login/**",
                				 "/css/**",
                				 "/registration/**").permitAll()				// public paths
                .requestMatchers("/admin/**").hasRole("admin")					// admin role pulled from db
                .requestMatchers("/user/**",
                				 "/images/**").hasAnyRole("user", "admin")		// user role pulled from db	
                .requestMatchers("/service/**").hasRole("API")					// API role pulled from db
                .anyRequest().authenticated()									// all other paths require authentication
            )

            // form login
            .formLogin(form -> form
                    .loginPage("/login/")                     
                    .loginProcessingUrl("/login/doLogin")      
                    .successHandler(successHandler)    
                    .failureUrl("/login/?error=true")         
                    .permitAll()
            )

            // logout
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessUrl("/")
                .permitAll()
            )
            
            .exceptionHandling(ex -> ex
                    // for any request matching /service/**, send 401 instead of redirect
                    .defaultAuthenticationEntryPointFor(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                        new AntPathRequestMatcher("/service/**")
                    )
                )
            
            // add the ApiTokenFilter before the UsernamePasswordAuthenticationFilter
            .addFilterBefore(new ApiTokenFilter(), UsernamePasswordAuthenticationFilter.class);
            
        return http.build();	// build the security filter chain 
    }
    /**
     * This method configures the authentication manager for the application.
     * @param http
     * @param encoder
     * @return Authentication manager bean
     * @throws Exception
     */
    @Bean
    AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder) throws Exception {   	
    	
    	AuthenticationManagerBuilder builder = 
    		http
    			.getSharedObject(AuthenticationManagerBuilder.class);		// Authentication manager builder
        	builder
        		.userDetailsService(loginService)							// User details service	
        		.passwordEncoder(encoder);									// Password encoder
        
        return builder.build();												// Authentication manager bean	
    }
    /**
	 * This method configures the password encoder for the application.
	 * @return Password encoder bean
	 */
    @Bean
    PasswordEncoder  passwordEncoder() {									// Password encoder bean
    	return new BCryptPasswordEncoder();									// BCrypt password encoder
    }
}

