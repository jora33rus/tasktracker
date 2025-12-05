package com.tasktracker.model.dto.response.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AbstractApiException {

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "Entity Not Found");
    }
}
