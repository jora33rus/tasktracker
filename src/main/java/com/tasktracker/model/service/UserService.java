package com.tasktracker.model.service;

import com.tasktracker.model.database.entity.User;
import com.tasktracker.model.dto.request.UserRequest;
import com.tasktracker.model.dto.response.exception.AlreadyExistsException;
import com.tasktracker.model.dto.response.exception.NotFoundException;
import com.tasktracker.model.dto.response.entity.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> list();

    UserResponse getById(Integer id) throws NotFoundException;

    void create(User user) throws AlreadyExistsException;

    UserResponse update(Integer id, UserRequest request) throws NotFoundException, AlreadyExistsException;

    void delete(Integer id) throws NotFoundException;

    void getAdmin() throws NotFoundException;
}
