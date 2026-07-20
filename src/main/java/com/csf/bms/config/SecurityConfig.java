package com.csf.bms.config;

import com.csf.bms.Service.CustomUserDetailsService;
import com.csf.bms.Service.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // REST API mein CSRF nahi chahiye
                .csrf(csrf -> csrf.disable())

                // Session store nahi karnge — JWT use karenge
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        // Public — token nahi chahiye
                        .requestMatchers(
                                "/api/users/register",
                                "/api/auth/login",
                                "/api/auth/register"
                        ).permitAll()

                        // ADMIN only — create/update/delete
                        .requestMatchers(HttpMethod.POST,
                                "/api/theaters/**",
                                "/api/screens/**",
                                "/api/movies/**",
                                "/api/shows/**",
                                "/api/seats/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT,
                                "/api/theaters/**",
                                "/api/screens/**",
                                "/api/movies/**",
                                "/api/shows/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE,
                                "/api/theaters/**",
                                "/api/screens/**",
                                "/api/movies/**",
                                "/api/shows/**"
                        ).hasRole("ADMIN")

                        // USER — booking aur payment
                        .requestMatchers(
                                "/api/bookings/**",
                                "/api/payments/**"
                        ).hasAnyRole("USER", "ADMIN")

                        // Baaki sab authenticated
                        .anyRequest().authenticated())

                // Har request mein pehle JWT check hoga
                .addFilterBefore(jwtFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // BCrypt — password encrypt karne ke liye
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Login ke time email + password verify karta hai
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}