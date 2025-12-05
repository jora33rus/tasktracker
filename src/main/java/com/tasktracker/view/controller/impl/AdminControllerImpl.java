package com.tasktracker.view.controller.impl;

import com.tasktracker.model.database.entity.Task;
import com.tasktracker.model.database.entity.TaskGroup;
import com.tasktracker.model.database.repository.TaskGroupRepository;
import com.tasktracker.model.database.repository.TaskRepository;
import com.tasktracker.model.dto.request.UserRequest;
import com.tasktracker.model.dto.response.entity.TaskGroupResponse;
import com.tasktracker.model.dto.response.entity.TaskResponse;
import com.tasktracker.model.dto.response.entity.UserResponse;
import com.tasktracker.model.dto.response.exception.AlreadyExistsException;
import com.tasktracker.model.dto.response.exception.NotFoundException;
import com.tasktracker.model.dto.response.message.DeletedResponse;
import com.tasktracker.model.mapper.TaskGroupMapper;
import com.tasktracker.model.mapper.TaskMapper;
import com.tasktracker.model.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@SecurityRequirement(name = "Auth")
public class AdminControllerImpl {

    private final UserService userService;
    private final TaskRepository taskRepository;
    private final TaskGroupRepository taskGroupRepository;
    private final TaskMapper taskMapper;
    private final TaskGroupMapper taskGroupMapper;

    public AdminControllerImpl(UserService userService,
                               TaskRepository taskRepository,
                               TaskGroupRepository taskGroupRepository,
                               TaskMapper taskMapper,
                               TaskGroupMapper taskGroupMapper) {
        this.userService = userService;
        this.taskRepository = taskRepository;
        this.taskGroupRepository = taskGroupRepository;
        this.taskMapper = taskMapper;
        this.taskGroupMapper = taskGroupMapper;
    }

    // === USERS ===
    @GetMapping("/users")
    @Operation(summary = "Get all users")
    public List<UserResponse> listUsers() {
        return userService.list();
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Get user by ID")
    public UserResponse getUserById(@PathVariable Integer id) throws NotFoundException {
        return userService.getById(id);
    }

    @PutMapping("/users/{id}")
    @Operation(summary = "Update user")
    public UserResponse updateUser(@PathVariable Integer id, @RequestBody UserRequest request)
            throws NotFoundException, AlreadyExistsException {
        return userService.update(id, request);
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Delete user")
    public DeletedResponse deleteUser(@PathVariable Integer id) throws NotFoundException {
        userService.delete(id);
        return new DeletedResponse();
    }

    // === TASKS (ADMIN) ===
    @GetMapping("/tasks")
    @Operation(summary = "Get all tasks in system")
    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepository.findAllByOrderByCreatedAtDesc();
        return taskMapper.asListResponse(tasks);
    }

    @GetMapping("/tasks/user/{userId}")
    @Operation(summary = "Get tasks by user ID")
    public List<TaskResponse> getTasksByUser(@PathVariable Integer userId) {
        List<Task> tasks = taskRepository.findByUserIdOrderByCreatedAtDesc(userId);
        return taskMapper.asListResponse(tasks);
    }

    // === GROUPS (ADMIN) ===
    @GetMapping("/groups")
    @Operation(summary = "Get all groups in system")
    public List<TaskGroupResponse> getAllGroups() {
        List<TaskGroup> groups = taskGroupRepository.findAllByOrderByCreatedAtDesc();
        return taskGroupMapper.asListResponse(groups);
    }

    // === STATISTICS ===
    @GetMapping("/statistics")
    @Operation(summary = "Get system statistics")
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();

        // Статистика по статусам задач (JPQL запрос)
        List<Object[]> statusStats = taskRepository.countTasksByStatus();
        Map<String, Long> statusMap = new HashMap<>();
        for (Object[] stat : statusStats) {
            statusMap.put(stat[0].toString(), (Long) stat[1]);
        }
        stats.put("tasksByStatus", statusMap);

        // Статистика по группам задач (JPQL запрос)
        List<Object[]> groupStats = taskRepository.countTasksByGroup();
        Map<String, Long> groupMap = new HashMap<>();
        for (Object[] stat : groupStats) {
            groupMap.put(stat[0].toString(), (Long) stat[1]);
        }
        stats.put("tasksByGroup", groupMap);

        // Общая статистика
        stats.put("totalUsers", userService.list().size());
        stats.put("totalTasks", taskRepository.count());
        stats.put("totalGroups", taskGroupRepository.count());

        return stats;
    }
}


