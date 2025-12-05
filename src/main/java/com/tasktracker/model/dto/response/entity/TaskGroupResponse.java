package com.tasktracker.model.dto.response.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TaskGroupResponse extends EntityResponse {
    private final String name;
    private final Integer userId;
    private final String userName;
    private final int taskCount;

    public TaskGroupResponse(Integer id, LocalDateTime createdAt, String name,
                             Integer userId, String userName, int taskCount) {
        super(id, createdAt);
        this.name = name;
        this.userId = userId;
        this.userName = userName;
        this.taskCount = taskCount;
    }
}