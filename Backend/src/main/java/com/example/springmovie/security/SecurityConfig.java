package com.example.springmovie.security;

import com.example.springmovie.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JWTRequestFilter jwtRequestFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        // permit all GET requests
                        .requestMatchers(HttpMethod.GET, "api/v1/movie/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/v1/actor/**").permitAll()
                        // permit POST for logging in and registering
                        .requestMatchers(HttpMethod.POST, "api/v1/auth/**").permitAll()
                        // only those who have the role user can do anything else
                        .requestMatchers("/**").hasRole(Role.USER.name())
                        // permit all
                        // .anyRequest().permitAll()
                        .anyRequest().authenticated()
                )
                // Configure session management to be stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Set the custom authentication provider
                .authenticationProvider(authenticationProvider)

                // Add JWT request filter before AuthorizationFilter
                .addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
        return http.build();
    }

}

