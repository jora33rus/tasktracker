package com.tasktracker.model.dto.request;

public record SignUpRequest(
        String email,

        String password,

        String firstName,
        String lastName
) {
}
