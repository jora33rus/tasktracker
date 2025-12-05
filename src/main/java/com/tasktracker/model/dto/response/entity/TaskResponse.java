package com.tasktracker.model.dto.response.entity;

import com.tasktracker.model.database.entity.TaskStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TaskResponse extends EntityResponse {
    private final String title;
    private final String description;
    private final TaskStatus status;
    private final Integer userId;
    private final String userName;
    private final Integer groupId;
    private final String groupName;

    public TaskResponse(Integer id, LocalDateTime createdAt, String title,
                        String description, TaskStatus status, Integer userId,
                        String userName, Integer groupId, String groupName) {
        super(id, createdAt);
        this.title = title;
        this.description = description;
        this.status = status;
        this.userId = userId;
        this.userName = userName;
        this.groupId = groupId;
        this.groupName = groupName;
    }
}