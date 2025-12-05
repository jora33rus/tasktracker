package com.tasktracker.model.dto.response.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public abstract class EntityResponse {
    protected Integer id;
    protected LocalDateTime createdAt;
}
