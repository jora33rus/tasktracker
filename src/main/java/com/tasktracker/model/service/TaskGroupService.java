package com.tasktracker.model.service;

import com.tasktracker.model.dto.request.TaskGroupRequest;
import com.tasktracker.model.dto.response.entity.TaskGroupResponse;
import com.tasktracker.model.dto.response.exception.NotFoundException;

import java.util.List;

public interface TaskGroupService {
    TaskGroupResponse createGroup(TaskGroupRequest request, Integer userId);
    List<TaskGroupResponse> getUserGroups(Integer userId);
    TaskGroupResponse getGroupById(Integer groupId, Integer userId) throws NotFoundException;
    TaskGroupResponse updateGroup(Integer groupId, TaskGroupRequest request, Integer userId) throws NotFoundException;
    void deleteGroup(Integer groupId, Integer userId) throws NotFoundException;
}