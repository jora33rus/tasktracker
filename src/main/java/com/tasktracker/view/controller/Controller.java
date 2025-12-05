package com.tasktracker.view.controller;

import com.tasktracker.model.dto.request.UserRequest;
import com.tasktracker.model.dto.response.message.DeletedResponse;
import com.tasktracker.model.dto.response.exception.NotFoundException;
import com.tasktracker.model.dto.response.entity.UserResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@SecurityRequirement(name = "Auth")
public interface Controller {
    List<UserResponse> list();

    UserResponse getById(Integer id) throws NotFoundException;

    UserResponse update(Integer id, UserRequest request) throws NotFoundException;

    DeletedResponse delete(Integer id) throws NotFoundException;

    void getAdmin() throws NotFoundException;
}
