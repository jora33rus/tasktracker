package com.tasktracker.model.dto.response.entity;

import com.tasktracker.model.database.entity.Role;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class UserResponse extends EntityResponse {
    private final String firstName;
    private final String lastName;
    private final String email;

    private final Role role;

    public UserResponse(Integer id, LocalDateTime createdAt, String firstName, String lastName, String email, Role role) {
        super(id, createdAt);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}
