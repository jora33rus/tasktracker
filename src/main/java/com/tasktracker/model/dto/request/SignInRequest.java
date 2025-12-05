package com.tasktracker.model.dto.request;

public record SignInRequest(
        String email,

        String password
) {
}
