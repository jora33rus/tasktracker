package com.tasktracker.model.service;

import com.tasktracker.model.dto.request.RefreshTokenRequest;
import com.tasktracker.model.dto.request.SignInRequest;
import com.tasktracker.model.dto.request.SignUpRequest;
import com.tasktracker.model.dto.response.exception.AlreadyExistsException;
import com.tasktracker.model.dto.response.exception.InvalidCredentialsException;
import com.tasktracker.model.dto.response.exception.InvalidJwtException;
import com.tasktracker.model.dto.response.message.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signUp(SignUpRequest request) throws AlreadyExistsException;

    JwtAuthenticationResponse signIn(SignInRequest request) throws InvalidJwtException, InvalidCredentialsException;

    JwtAuthenticationResponse refreshAccessToken(RefreshTokenRequest request) throws InvalidJwtException;

}
