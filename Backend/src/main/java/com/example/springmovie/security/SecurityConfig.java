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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/**",
            "/v1/api-docs/**",
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                //                .authorizeHttpRequests(req -> req
                //                                .anyRequest().permitAll()
                //                        //                        .requestMatchers(WHITE_LIST_URL).permitAll()
                //                        // allow only requests if user has USER authority
                //                        //                        .requestMatchers("api/v1/movie/**").hasAnyAuthority(Role.USER.name())
                //                        // allow all requests if user has any authority
                //                        //                        .anyRequest().authenticated()
                //                )

                .authorizeHttpRequests(req -> req
                                .requestMatchers(HttpMethod.GET, "api/v1/movie/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                                .requestMatchers(HttpMethod.POST, "api/v1/movie/**").hasAnyAuthority(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.PUT, "api/v1/movie/**").hasAnyAuthority(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.DELETE, "api/v1/movie/**").hasAnyAuthority(Role.ADMIN.name())
                                .requestMatchers("api/v1/genre/**").hasAnyAuthority(Role.ADMIN.name())
                                .requestMatchers(WHITE_LIST_URL).permitAll()

                        // allow all requests if user has any authority
                        //                        .anyRequest().authenticated()
                )

                // SessionManagement configures session management
                // stateless means that spring security will never create an httpsession and it will never use it to obtain the securitycontext
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                // set the AuthenticationProvider to be used
                .authenticationProvider(authenticationProvider)
                // addfilterbefore adds the JwtAuthenticationFilter before usernamepasswordauthenticationfilter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

