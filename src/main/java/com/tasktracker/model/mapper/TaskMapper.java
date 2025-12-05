package com.tasktracker.model.mapper;

import com.tasktracker.model.database.entity.Task;
import com.tasktracker.model.dto.response.entity.TaskResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    public TaskResponse asResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getCreatedAt(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getUser().getId(),
                task.getUser().getFirstName() + " " + task.getUser().getLastName(),
                task.getGroup() != null ? task.getGroup().getId() : null,
                task.getGroup() != null ? task.getGroup().getName() : null
        );
    }

    public List<TaskResponse> asListResponse(List<Task> tasks) {
        List<TaskResponse> response = new ArrayList<>();
        for (Task task : tasks) {
            response.add(asResponse(task));
        }
        return response;
    }
}