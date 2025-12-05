package com.tasktracker.view.controller.impl;

import com.tasktracker.model.dto.response.exception.NotFoundException;
import com.tasktracker.model.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "Auth")
public class UserControllerImpl {

    private final UserService service;

    public UserControllerImpl(UserService service) {
        this.service = service;
    }

    @GetMapping("/get-admin")
    @Operation(summary = "Get admin role (for testing)")
    @PreAuthorize("isAuthenticated()")  // ДОБАВИТЬ ЭТУ АННОТАЦИЮ!
    public void getAdmin() throws NotFoundException {
        service.getAdmin();
    }
}

