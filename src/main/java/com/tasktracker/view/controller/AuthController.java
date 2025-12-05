package com.tasktracker.view.controller;

import com.tasktracker.model.dto.request.RefreshTokenRequest;
import com.tasktracker.model.dto.request.SignInRequest;
import com.tasktracker.model.dto.request.SignUpRequest;
import com.tasktracker.model.dto.response.exception.AlreadyExistsException;
import com.tasktracker.model.dto.response.exception.InvalidCredentialsException;
import com.tasktracker.model.dto.response.exception.InvalidJwtException;
import com.tasktracker.model.dto.response.message.JwtAuthenticationResponse;

public interface AuthController {

    JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) throws AlreadyExistsException;

    JwtAuthenticationResponse signIn(SignInRequest signInRequest) throws InvalidJwtException, InvalidCredentialsException;

    JwtAuthenticationResponse refreshAccessToken(RefreshTokenRequest refreshTokenRequest) throws InvalidJwtException;
}
