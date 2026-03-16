package com.admin.admine_management.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.admin.admine_management.Service.AdminUserDetailService;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private AdminUserDetailService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Filters
        //disable csrf
        http.csrf(customizer -> customizer.disable())
        //authorize requests
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/login", "/css/**").permitAll()  // Allow login page & CSS
            .anyRequest().authenticated()                       // Everything else needs login
        )
        //web login
        // http.formLogin(Customizer.withDefaults());
        .formLogin(form -> form
            .loginPage("/admin/login")                                // Your custom login page
            .defaultSuccessUrl("/admin/dashboard", true)                    // Where to go after login
            .permitAll()
        )
        //logout
        .logout(logout -> logout
            .logoutSuccessUrl("/admin/login?logout")                  // Where to go after logout
            .permitAll()
        )
        //for postman
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        );
        return http.build();
    }

    @Bean
    //AuthenticationProvider is an interface in Spring Security
//     Verifying username
//      Verifying password
//      Authenticating the user
    public AuthenticationProvider authenticationProvider() {
        //Database authentication "DaoAuthenticationProvider", DAO = Data Access Object
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //Password Encoder
        //input password → BCrypt hash → compare with stored hash,  10 = strength
        provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        //Loading user data from database.
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
