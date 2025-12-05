package com.tasktracker.model.dto.response.message;

import lombok.Getter;

@Getter
public class JwtAuthenticationResponse extends AbstractApiMessage {

    public JwtAuthenticationResponse(String accessToken, String refreshToken, String role) {
        super("Successfully authenticated.");
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.role = role;
    }

    private final String accessToken;
    private final String refreshToken;
    private final String role;

}
