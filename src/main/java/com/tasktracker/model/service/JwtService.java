package com.tasktracker.model.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;


public interface JwtService {
    boolean isTokenValid(String token, UserDetails userDetails);

    String extractUsername(String token);

    String generateToken(UserDetails userDetails, Date expirationDate);
}
