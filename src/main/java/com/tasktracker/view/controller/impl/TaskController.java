package com.tasktracker.view.controller.impl;

import com.tasktracker.model.database.entity.TaskStatus;
import com.tasktracker.model.dto.request.TaskRequest;
import com.tasktracker.model.dto.request.TaskUpdateRequest;
import com.tasktracker.model.dto.response.entity.TaskResponse;
import com.tasktracker.model.dto.response.exception.NotFoundException;
import com.tasktracker.model.dto.response.message.DeletedResponse;
import com.tasktracker.model.service.TaskService;
import com.tasktracker.util.SecurityHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "Auth")
public class TaskController {

    private final TaskService taskService;
    private final SecurityHelper securityHelper;

    public TaskController(TaskService taskService, SecurityHelper securityHelper) {
        this.taskService = taskService;
        this.securityHelper = securityHelper;
    }

    @GetMapping("/tasks")
    @Operation(summary = "Get all tasks for current user")
    @PreAuthorize("isAuthenticated()")
    public List<TaskResponse> getUserTasks() {
        try {
            Integer userId = securityHelper.getCurrentUserId();
            System.out.println("Getting tasks for user ID: " + userId);
            return taskService.getUserTasks(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/tasks/{id}")
    @Operation(summary = "Get task by ID")
    @PreAuthorize("isAuthenticated()")
    public TaskResponse getTaskById(@PathVariable Integer id) throws NotFoundException {
        Integer userId = securityHelper.getCurrentUserId();
        return taskService.getTaskById(id, userId);
    }

    @PostMapping("/tasks")
    @Operation(summary = "Create new task")
    @PreAuthorize("isAuthenticated()")
    public TaskResponse createTask(@RequestBody TaskRequest request) {
        try {
            System.out.println("Creating task with data: " + request);
            Integer userId = securityHelper.getCurrentUserId();
            System.out.println("For user ID: " + userId);
            return taskService.createTask(request, userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PutMapping("/tasks/{id}")
    @Operation(summary = "Update task")
    @PreAuthorize("isAuthenticated()")
    public TaskResponse updateTask(@PathVariable Integer id, @RequestBody TaskUpdateRequest request)
            throws NotFoundException {
        Integer userId = securityHelper.getCurrentUserId();
        return taskService.updateTask(id, request, userId);
    }

    @PatchMapping("/tasks/{id}/status")
    @Operation(summary = "Update task status")
    @PreAuthorize("isAuthenticated()")
    public TaskResponse updateTaskStatus(@PathVariable Integer id, @RequestBody TaskStatus status)
            throws NotFoundException {
        Integer userId = securityHelper.getCurrentUserId();
        return taskService.updateTaskStatus(id, status, userId);
    }

    @DeleteMapping("/tasks/{id}")
    @Operation(summary = "Delete task")
    @PreAuthorize("isAuthenticated()")
    public DeletedResponse deleteTask(@PathVariable Integer id) throws NotFoundException {
        Integer userId = securityHelper.getCurrentUserId();
        taskService.deleteTask(id, userId);
        return new DeletedResponse();
    }
}