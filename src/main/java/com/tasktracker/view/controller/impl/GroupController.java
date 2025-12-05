package com.tasktracker.view.controller.impl;

import com.tasktracker.model.dto.request.TaskGroupRequest;
import com.tasktracker.model.dto.response.entity.TaskGroupResponse;
import com.tasktracker.model.dto.response.exception.NotFoundException;
import com.tasktracker.model.dto.response.message.DeletedResponse;
import com.tasktracker.model.service.TaskGroupService;
import com.tasktracker.util.SecurityHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "Auth")
public class GroupController {

    private final TaskGroupService taskGroupService;
    private final SecurityHelper securityHelper;

    public GroupController(TaskGroupService taskGroupService, SecurityHelper securityHelper) {
        this.taskGroupService = taskGroupService;
        this.securityHelper = securityHelper;
    }

    @PostMapping("/groups")
    @Operation(summary = "Create new task group")
    @PreAuthorize("isAuthenticated()")
    public TaskGroupResponse createGroup(@RequestBody TaskGroupRequest request) {
        Integer userId = securityHelper.getCurrentUserId();
        return taskGroupService.createGroup(request, userId);
    }

    @GetMapping("/groups")
    @Operation(summary = "Get all groups for current user")
    @PreAuthorize("isAuthenticated()")
    public List<TaskGroupResponse> getUserGroups() {
        Integer userId = securityHelper.getCurrentUserId();
        return taskGroupService.getUserGroups(userId);
    }

    @GetMapping("/groups/{id}")
    @Operation(summary = "Get group by ID")
    @PreAuthorize("isAuthenticated()")
    public TaskGroupResponse getGroupById(@PathVariable Integer id) throws NotFoundException {
        Integer userId = securityHelper.getCurrentUserId();
        return taskGroupService.getGroupById(id, userId);
    }

    @PutMapping("/groups/{id}")
    @Operation(summary = "Update group name")
    @PreAuthorize("isAuthenticated()")
    public TaskGroupResponse updateGroup(@PathVariable Integer id, @RequestBody TaskGroupRequest request)
            throws NotFoundException {
        Integer userId = securityHelper.getCurrentUserId();
        return taskGroupService.updateGroup(id, request, userId);
    }

    @DeleteMapping("/groups/{id}")
    @Operation(summary = "Delete group")
    @PreAuthorize("isAuthenticated()")
    public DeletedResponse deleteGroup(@PathVariable Integer id) throws NotFoundException {
        Integer userId = securityHelper.getCurrentUserId();
        taskGroupService.deleteGroup(id, userId);
        return new DeletedResponse();
    }
}