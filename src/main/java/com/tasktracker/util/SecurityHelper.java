package com.tasktracker.util;

import com.tasktracker.model.database.entity.User;
import com.tasktracker.model.database.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityHelper {

    private final UserRepository userRepository;

    public SecurityHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer getCurrentUserId() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found for email: " + email);
        }
        return user.getId();
    }

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found for email: " + email);
        }
        return user;
    }
}