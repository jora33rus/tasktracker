package com.tasktracker.model.dto.request;

import com.tasktracker.model.database.entity.TaskStatus;

public record TaskUpdateRequest(
        String title,
        String description,
        TaskStatus status,
        Integer groupId
) {}