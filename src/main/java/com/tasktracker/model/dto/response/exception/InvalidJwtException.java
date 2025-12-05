package com.tasktracker.model.dto.response.exception;

import org.springframework.http.HttpStatus;

public class InvalidJwtException extends AbstractApiException {

    public InvalidJwtException() {
        super(HttpStatus.FORBIDDEN, "JWT Token is malformed or does not exist.");
    }
}
