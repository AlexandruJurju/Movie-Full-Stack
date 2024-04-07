package com.example.springmovie.security;

import com.example.springmovie.service.JWTService;
import com.example.springmovie.service.interfaces.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        // extract the authorization header from the request
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);

        // if the authorization header is empty or does not start with "Bearer ", continue to the next filter
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        // extract the jwt token from the authorization header
        String jwtToken = extractJwtToken(authHeader);

        // extract the username from the jwt token
        String username = jwtService.extractUsername(jwtToken);

        // if the username is not null and the user is not already authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // load the user details
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);

            // if the token is valid, set the authentication in the securitycontext
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                setAuthenticationInContext(userDetails, request);
            }
        }

        // continue filtering to the next chain link
        filterChain.doFilter(request, response);
    }

    private String extractJwtToken(String authHeader) {
        return authHeader.substring(BEARER_PREFIX.length());
    }

    private void setAuthenticationInContext(UserDetails userDetails, HttpServletRequest request) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
    }
}