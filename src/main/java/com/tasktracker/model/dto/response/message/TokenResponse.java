package com.tasktracker.model.dto.response.message;

import lombok.Getter;

@Getter
public class TokenResponse extends AbstractApiMessage {

    public TokenResponse(String token, String role) {
        super("Successfully authenticated.");
        this.token = token;
        this.role = role;
    }

    private final String token;
    private final String role;
}