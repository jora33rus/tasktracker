package com.tasktracker.model.dto.request;

public record TaskRequest(
        String title,
        String description,
        Integer groupId  // может быть null, если задача без группы
) {}