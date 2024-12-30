package com.PLACEMENTWEBAPP.PlacementWebApp.Configuration;

import com.PLACEMENTWEBAPP.PlacementWebApp.Service.StudentService;
import com.PLACEMENTWEBAPP.PlacementWebApp.Service.TokenGenerator;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private TokenGenerator otpService;
    @Autowired
    private StudentService studentService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        System.out.println("JwtRequestFilter invoked for: " + request.getRequestURI());
        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println(headerName + ": " + request.getHeader(headerName));
//        }


//        System.out.println(request);

        final String authorizationHeader = request.getHeader("Authorization");

        String jwt = null;
        String username = null;
        System.out.println("authorizationHeader"+authorizationHeader);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            System.out.println("Authorization Header: " + authorizationHeader);

            jwt = authorizationHeader.substring(7); // Extract the token
            try {
                username = otpService.extractUserName(jwt); // Decode to get the username
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
                return;

            } // Decode to get the username
        }
        System.out.println("username"+username);


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("hi");
            UserDetails userDetails = null;
            try {
                userDetails = studentService.userDetailsService().loadUserByUsername(username);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Validate Token
            if (otpService.validateToken(jwt, userDetails)) {
                System.out.println("validating..");
                // Set authentication in Security Context
                Long userIdFromToken = otpService.extractClaim(jwt, claims -> claims.get("userId", Long.class));
                Claims claims = otpService.extractAllClaims(jwt);

                String role = claims.get("role", String.class);
                System.out.println("Extracted Role: " + role);

                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authToken);
                request.setAttribute("userId",claims.get("userId", Long.class));
                System.out.println("Authorities: " + userDetails.getAuthorities());
                System.out.println("Parsed Role from JWT: " + role);


                System.out.println("Token valid. Adding userId to request: " + userIdFromToken);


            } else {
                System.out.println("invalid token");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or Expired Token");
                return;
            }
        }
        else{
            System.out.println("something happens");
        }
        chain.doFilter(request, response);
    }
}


