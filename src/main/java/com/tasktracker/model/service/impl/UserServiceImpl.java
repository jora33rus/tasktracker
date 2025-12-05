package com.tasktracker.model.service.impl;

import com.tasktracker.model.database.entity.Role;
import com.tasktracker.model.database.entity.User;
import com.tasktracker.model.database.repository.UserRepository;
import com.tasktracker.model.dto.request.UserRequest;
import com.tasktracker.model.dto.response.entity.UserResponse;
import com.tasktracker.model.dto.response.exception.AlreadyExistsException;
import com.tasktracker.model.dto.response.exception.NotFoundException;
import com.tasktracker.model.mapper.UserMapper;
import com.tasktracker.model.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;
    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserMapper mapper, UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<UserResponse> list() {
        return mapper.asListResponse(repository.findAll());
    }

    @Override
    public UserResponse getById(Integer id) throws NotFoundException {
        User user = repository.findById(id).orElseThrow(NotFoundException::new);
        return mapper.asResponse(user);
    }

    @Override
    public void create(User user) throws AlreadyExistsException {
        if (repository.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException("User with this email already exists.");
        }
        repository.save(user);
    }

    @Transactional
    @Override
    public UserResponse update(Integer id, @Valid UserRequest request) throws NotFoundException, AlreadyExistsException {
        User user = repository.findById(id).orElseThrow(NotFoundException::new);

        // Проверяем, не пытаются ли изменить email на уже существующий
        if (!user.getEmail().equals(request.email()) &&
                repository.existsByEmail(request.email())) {
            throw new AlreadyExistsException("User with this email already exists.");
        }

        user = mapper.update(user, request);
        return mapper.asResponse(user);
    }

    @Transactional
    @Override
    public void delete(Integer id) throws NotFoundException {
        User user = repository.findById(id).orElseThrow(NotFoundException::new);
        repository.delete(user);
    }

    // ДОБАВЛЯЕМ ОБРАТНО МЕТОД getAdmin()
    @Override
    public void getAdmin() throws NotFoundException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = repository.findByEmail(email);
        if (user == null)
            throw new NotFoundException();
        user.setRole(Role.ROLE_ADMIN);
        repository.save(user);
    }
}