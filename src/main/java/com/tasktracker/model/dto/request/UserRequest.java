package com.tasktracker.model.dto.request;
public record UserRequest(
        String firstName,
        String lastName,
        String email
) {}
