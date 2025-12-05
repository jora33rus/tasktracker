package com.tasktracker.view.controller.impl;

import com.tasktracker.model.dto.request.RefreshTokenRequest;
import com.tasktracker.model.dto.request.SignInRequest;
import com.tasktracker.model.dto.request.SignUpRequest;
import com.tasktracker.model.dto.response.exception.AlreadyExistsException;
import com.tasktracker.model.dto.response.exception.InvalidCredentialsException;
import com.tasktracker.model.dto.response.exception.InvalidJwtException;
import com.tasktracker.model.dto.response.message.JwtAuthenticationResponse;
import com.tasktracker.model.service.AuthenticationService;
import com.tasktracker.view.controller.AuthController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthControllerImpl implements AuthController {

    private final AuthenticationService authenticationService;

    public AuthControllerImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest signUpRequest) throws AlreadyExistsException {
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest signInRequest) throws InvalidJwtException, InvalidCredentialsException {
        return authenticationService.signIn(signInRequest);
    }

    @PostMapping("/refresh")
    public JwtAuthenticationResponse refreshAccessToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws InvalidJwtException {
        return authenticationService.refreshAccessToken(refreshTokenRequest);
    }
}
