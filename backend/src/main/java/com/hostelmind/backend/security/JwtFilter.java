package com.hostelmind.backend.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("========== JWT FILTER ==========");
        System.out.println("URI : " + request.getRequestURI());

        String authHeader = request.getHeader("Authorization");

        System.out.println("Authorization Header : " + authHeader);

        if (authHeader == null) {
            System.out.println("Authorization header is NULL");
            filterChain.doFilter(request, response);
            return;
        }

        if (!authHeader.startsWith("Bearer ")) {
            System.out.println("Header does not start with Bearer");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        System.out.println("Token : " + token);

        try {

            String username = jwtService.extractUsername(token);

            System.out.println("Username : " + username);

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(username);

            System.out.println("Authorities : " + userDetails.getAuthorities());

            if (jwtService.validateToken(token, username)) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

                System.out.println("AUTHENTICATION SUCCESS");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }
}